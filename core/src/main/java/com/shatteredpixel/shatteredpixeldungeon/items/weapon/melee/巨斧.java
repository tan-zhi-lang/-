

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class 巨斧 extends MeleeWeapon {

	{
		image = 物品表.巨斧;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1f;

		tier = 5;
	}

	@Override
	public int 最大攻击(int lvl) {
		return  5*(tier+4) +    //45 base, up from 30
				lvl*(tier+1);   //scaling unchanged
	}

	@Override
	public int 力量(int lvl) {
		int req = 力量(tier+1, lvl); //20 base strength req, up from 18
		if (masteryPotionBonus){
			req -= 2;
		}
		return req;
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		if (hero.生命 / (float)hero.最大生命 >= 0.5f){
			GLog.w(Messages.get(this, "ability_cant_use"));
			return;
		}

		if (target == null) {
			return;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(this, "ability_no_target"));
			return;
		}

		hero.belongings.abilityWeapon = this;
		if (!hero.canAttack(enemy)){
			GLog.w(Messages.get(this, "ability_target_range"));
			hero.belongings.abilityWeapon = null;
			return;
		}
		hero.belongings.abilityWeapon = null;

		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
				beforeAbilityUsed(hero, enemy);
				AttackIndicator.target(enemy);

				//+(15+(2*lvl)) damage, roughly +60% base damage, +55% scaling
				int dmgBoost = augment.damageFactor(15 + 2* 强化等级());

				if (hero.attack(enemy, 1, dmgBoost, Char.INFINITE_ACCURACY)){
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
				}

				Invisibility.notimedispel();
				if (!enemy.isAlive()){
					hero.next();
					onAbilityKill(hero, enemy);
				} else {
					hero.spendAndNext(hero.攻速());
				}
				afterAbilityUsed(hero);
			}
		});
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 15 + 2* 强化等级() : 15;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0)+dmgBoost, 最大攻击(0)+dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 15 + 2*level;
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "-" + augment.damageFactor(最大攻击(level)+dmgBoost);
	}
}

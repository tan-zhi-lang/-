

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class 矛盾 extends MeleeWeapon {

	{
		image = 物品表.矛盾;
		
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 0.9f;
		
		tier = 1;
		命中= 0.6f;
		间隔= 1.5f;
		伤害= 1.8f;
		范围 = 2;    //extra reach
		
		bones = false;
	}
	
	@Override
	public int 力量(int lvl) {
		int req = 力量(tier, lvl)+1;
		if (masteryPotionBonus){
			req -= 2;
		}
		if (神力){
			req -= 2;
		}
		return req;
	}
	@Override
	public int 最大防御(int lvl){
		return 2 + lvl;
	}
	
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}
	
	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		//+(9+2*lvl) damage, roughly +83% base damage, +80% scaling
		int dmgBoost = augment.damageFactor(9 + Math.round(2f* 强化等级()));
		Spear.spikeAbility(hero, target, 1, dmgBoost, this);
	}
	
	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 9 + Math.round(2f* 强化等级()) : 9;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0)+dmgBoost, this.最大攻击(0)+dmgBoost);
		}
	}
	
	public String upgradeAbilityStat(int level){
		int dmgBoost = 9 + Math.round(2f*level);
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "-" + augment.damageFactor(this.最大攻击(level)+dmgBoost);
	}
	
	public static void spikeAbility(Hero hero, Integer target, float dmgMulti, int dmgBoost, MeleeWeapon wep){
		if (target == null) {
			return;
		}
		
		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(wep,"ability_no_target"));
			return;
		}
		
		hero.belongings.abilityWeapon = wep;
		if (!hero.canAttack(enemy) || Dungeon.level.adjacent(hero.pos, enemy.pos)){
			GLog.w(Messages.get(wep, "ability_target_range"));
			hero.belongings.abilityWeapon = null;
			return;
		}
		hero.belongings.abilityWeapon = null;
		
		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
				wep.beforeAbilityUsed(hero, enemy);
				AttackIndicator.target(enemy);
				int oldPos = enemy.pos;
				//do not push if enemy has moved, or another push is active (e.g. elastic)
				if (hero.attack(enemy, dmgMulti, dmgBoost, Char.INFINITE_ACCURACY)) {
					if (enemy.isAlive() && enemy.pos == oldPos && !Pushing.pushingExistsForChar(enemy)){
						//trace a ballistica to our target (which will also extend past them
						Ballistica
								trajectory = new Ballistica(hero.pos,enemy.pos,Ballistica.STOP_TARGET);
						//trim it to just be the part that goes past them
						trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);
						//knock them back along that ballistica
						WandOfBlastWave.throwChar(enemy,trajectory,1,true,false,hero);
					} else if (!enemy.isAlive()) {
						wep.onAbilityKill(hero, enemy);
					}
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
				}
				Invisibility.notimedispel();
				hero.spendAndNext(hero.攻速());
				wep.afterAbilityUsed(hero);
			}
		});
	}

}

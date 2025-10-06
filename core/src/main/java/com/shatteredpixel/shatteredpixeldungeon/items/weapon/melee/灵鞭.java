

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Callback;

import java.util.ArrayList;

public class 灵鞭 extends MeleeWeapon {

	{
		image = 物品表.灵鞭;
		hitSoundPitch = 1.1f;

		tier = 1;
		命中=0.7f;
		间隔= 1.2f;
		伤害= 1.4f;
		范围 = 3;    //lots of extra reach
		unique = true;
		bones = false;
	}
	@Override
	public int 最小攻击(int lvl) {
		int dmg =super.最小攻击(lvl) + Dungeon.hero.等级(Dungeon.hero.天赋点数(Talent.放逐之鞭,0.04f));
		return Math.max(0, dmg);
	}
	
	@Override
	public int 最大攻击(int lvl) {
		int dmg =super.最大攻击(lvl) + Dungeon.hero.等级(Dungeon.hero.天赋点数(Talent.放逐之鞭,0.4f));
		return Math.max(0, dmg);
	}
	@Override
	protected void duelistAbility(Hero hero, Integer target) {

		ArrayList<Char> targets = new ArrayList<>();
		Char closest = null;

		hero.belongings.abilityWeapon = this;
		for (Char ch : Actor.chars()){
			if (ch.alignment == Char.Alignment.ENEMY
					&& !hero.isCharmedBy(ch)
					&& Dungeon.level.heroFOV[ch.pos]
					&& hero.canAttack(ch)){
				targets.add(ch);
				if (closest == null || Dungeon.level.trueDistance(hero.pos, closest.pos) > Dungeon.level.trueDistance(hero.pos, ch.pos)){
					closest = ch;
				}
			}
		}
		hero.belongings.abilityWeapon = null;

		if (targets.isEmpty()) {
			GLog.w(Messages.get(this, "ability_no_target"));
			return;
		}

		throwSound();
		Char finalClosest = closest;
		hero.sprite.attack(hero.pos, new Callback() {
			@Override
			public void call() {
				beforeAbilityUsed(hero, finalClosest);
				for (Char ch : targets) {
					//ability does no extra damage
					hero.attack(ch, 1, 0, Char.INFINITE_ACCURACY);
					if (!ch.isAlive()){
						onAbilityKill(hero, ch);
					}
				}
				Invisibility.notimedispel();
				hero.spendAndNext(hero.攻速());
				afterAbilityUsed(hero);
			}
		});
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()), augment.damageFactor(最大攻击()));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0), 最大攻击(0));
		}
	}

	public String upgradeAbilityStat(int level){
		return augment.damageFactor(最小攻击(level)) + "-" + augment.damageFactor(最大攻击(level));
	}
}

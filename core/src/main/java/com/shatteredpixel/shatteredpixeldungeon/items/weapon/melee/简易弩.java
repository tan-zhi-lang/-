

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 简易弩 extends MeleeWeapon {
	
	{
		image = 物品表.CROSSBOW;
		
		//check Dart.class for additional properties
		
		bones = false;
		tier = 1;
		命中= 1.1f;
		间隔= 0.9f;
		伤害= 0.8f;
	}

	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		if (super.doUnequip(hero, collect, single)){
			if (hero.buff(ChargedShot.class) != null &&
					!(hero.belongings.weapon() instanceof 简易弩)
					&& !(hero.belongings.secondWep() instanceof 简易弩)){
				//clear charged shot if no crossbow is equipped
				hero.buff(ChargedShot.class).detach();
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public float accuracyFactor(Char owner, Char target) {
		if (owner.buff(简易弩.ChargedShot.class)!=null){
			Actor.add(new Actor() {
				{ actPriority = VFX_PRIO; }
				@Override
				protected boolean act() {
					if (owner instanceof Hero && !target.isAlive()){
						onAbilityKill((Hero)owner, target);
					}
					Actor.remove(this);
					return true;
				}
			});
			return Float.POSITIVE_INFINITY;
		} else {
			return super.accuracyFactor(owner, target);
		}
	}

	@Override
	public int 攻击时(Char attacker, Char defender, int damage) {
		int dmg = super.攻击时(attacker, defender, damage);

		//stronger elastic effect
		if (attacker==Dungeon.hero
			&& Dungeon.hero.buff(ChargedShot.class) != null
			//not proccing from a dart
			&& Dungeon.hero.belongings.attackingWeapon() == this){
			//trace a ballistica to our target (which will also extend past them
			Ballistica trajectory = new Ballistica(attacker.pos, defender.pos, Ballistica.STOP_TARGET);
			//trim it to just be the part that goes past them
			trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
			//knock them back along that ballistica
			WandOfBlastWave.throwChar(defender,
					trajectory,
					4,
					true,
					true,
					this);
			attacker.buff(简易弩.ChargedShot.class).detach();
		}
		return dmg;
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		if (hero.buff(ChargedShot.class) != null){
			GLog.w(Messages.get(this, "ability_cant_use"));
			return;
		}

		beforeAbilityUsed(hero, null);
		Buff.施加(hero, ChargedShot.class);
		hero.sprite.operate();
		hero.next();
		afterAbilityUsed(hero);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 3+ 强化等级(), 3+ 强化等级());
		} else {
			return Messages.get(this, "typical_ability_desc", 3, 3);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(3 + level);
	}

	public static class ChargedShot extends Buff{

		{
			announced = true;
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.DUEL_XBOW;
		}

	}

}

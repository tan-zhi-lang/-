

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public class 死神 extends Weapon.Enchantment {

	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {
		if(defender!=null){
			if(defender.免疫(死神.class)){
				return damage;
			}


			float maxChance=0.35f
							*procChanceMultiplier(attacker)*attacker.enchantmentlevel(死神.class);

			Buff.施加(defender,GrimTracker.class).maxChance=maxChance;

			if(defender.buff(GrimTracker.class)!=null&&attacker instanceof Hero&&weapon.hasEnchant(死神.class,attacker)){
				defender.buff(GrimTracker.class).qualifiesForBadge=true;
			}
		}
		return damage;
	}


	public static class GrimTracker extends Buff {

		{
			actPriority = Actor.VFX_PRIO;
		}

		public float maxChance;
		public boolean qualifiesForBadge;

		@Override
		public boolean act() {
			detach();
			return true;
		}
	};

}

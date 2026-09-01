

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public class 寒霜 extends Weapon.Enchantment {

	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {
		if(defender!=null){
			if(attacker instanceof Hero hero){

				Buff.施加(defender,Chill.class,1*procChanceMultiplier(attacker)
											   *hero.enchantmentlevel(寒霜.class));
				Splash.at(defender.sprite.center(),0xFFB2D6FF,5);
			}
		}

		return damage;
	}


}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.watabou.utils.Random;

public class 血饮 extends Weapon.Enchantment {

	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {
		if(defender!=null){
			if(Random.Float()<defender.根据已损失生命()
							  *procChanceMultiplier(attacker)){
				if(attacker.isAlive()){
					attacker.回血(damage*0.04f
								  *procChanceMultiplier(attacker)*attacker.enchantmentlevel(血饮.class));
				}
			}
		}
		return damage;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;

public class 血祭 extends Weapon.Enchantment {


	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {
		if(defender!=null){
			damage*=1+attacker.根据已损失生命()*0.5f
					  *procChanceMultiplier(attacker);
		}
		return damage;
	}
	
	@Override
	public Glowing glowing() {
		return 深红;
	}
}

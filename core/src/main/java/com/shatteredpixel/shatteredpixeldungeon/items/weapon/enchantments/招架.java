

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;

public class 招架 extends Weapon.Enchantment {
	
	private static ItemSprite.Glowing BLUE = new ItemSprite.Glowing( 0x0000FF );
	
	@Override
	public float proc(Weapon weapon, Char attacker, Char defender, float damage) {

		if(defender!=null){
			attacker.护甲(2*procChanceMultiplier(attacker)*attacker.enchantmentlevel(招架.class));
		}
		return damage;
	}
}

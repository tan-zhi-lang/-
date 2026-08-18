

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

public class 极化 extends Weapon.Enchantment {

	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {

		if (Random.Float() < 1/2f) {
			return 2*damage* procChanceMultiplier(attacker);
		} else {
			return 0;
		}
		
	}

	
	@Override
	public ItemSprite.Glowing glowing() {
		return 黑;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;

public class 紊乱 extends Weapon.Enchantment {


	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {

		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return 灰;
	}
}

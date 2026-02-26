

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import static com.shatteredpixel.shatteredpixeldungeon.items.Item.updateQuickslot;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.Random;

public class 传说 extends Weapon.Enchantment {
	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {
		return damage;

	}
	
	@Override
	public Glowing glowing() {
		return Random.oneOf(
				new ItemSprite.Glowing(0xFF4444 ),
				new ItemSprite.Glowing(0xFFFF00 ),
				new ItemSprite.Glowing(0x3399FF ),
				new ItemSprite.Glowing(0x44FF44 ),

				new ItemSprite.Glowing(0xb2f2ff ),
				new ItemSprite.Glowing(0x2c0d49 ),
				new ItemSprite.Glowing(0x8800FF )
							);
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ClothArmor extends Armor {

	{
		image = 物品表.ARMOR_CLOTH;

		bones = false; //Finding them in bones would be semi-frequent and disappointing.
	}
	
	public ClothArmor() {
		super( 1 );
	}

}

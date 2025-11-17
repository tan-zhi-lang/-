

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class PlateArmor extends Armor {

	{
		image = 物品表.ARMOR_PLATE;
		换甲=Assets.Sounds.板甲;
	}
	
	public PlateArmor() {
		super( 5 );
	}

}

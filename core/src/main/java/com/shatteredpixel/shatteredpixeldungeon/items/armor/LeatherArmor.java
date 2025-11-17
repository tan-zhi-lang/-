

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class LeatherArmor extends Armor {

	{
		image = 物品表.ARMOR_LEATHER;
		换甲=Assets.Sounds.皮甲;
	}
	
	public LeatherArmor() {
		super( 2 );
	}

}

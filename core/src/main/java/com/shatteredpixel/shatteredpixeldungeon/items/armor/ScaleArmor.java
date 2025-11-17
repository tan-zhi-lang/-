

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ScaleArmor extends Armor {

	{
		image = 物品表.ARMOR_SCALE;
		换甲=Assets.Sounds.鳞甲;
	}
	
	public ScaleArmor() {
		super( 4 );
	}

}

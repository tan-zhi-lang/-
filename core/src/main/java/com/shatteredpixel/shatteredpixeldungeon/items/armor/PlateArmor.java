

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.护甲修理工具包;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class PlateArmor extends Armor {

	{
		image = 物品表.ARMOR_PLATE;
		换甲=Assets.Sounds.板甲;
	}
	
	public PlateArmor() {
		super( 5 );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{ScaleArmor.class,
					护甲修理工具包.class,};
			inQuantity = new int[]{1,1,};

			cost = 10;

			output = PlateArmor.class;
			outQuantity = 1;
		}

	}
}

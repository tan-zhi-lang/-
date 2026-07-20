

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.护甲修理工具包;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class LeatherArmor extends Armor {

	{
		image = 物品表.ARMOR_LEATHER;
		换甲=Assets.Sounds.皮甲;
	}
	
	public LeatherArmor() {
		super( 2 );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{ClothArmor.class,
					护甲修理工具包.class,};
			inQuantity = new int[]{1,1,};

			cost = 4;

			output = LeatherArmor.class;
			outQuantity = 1;
		}

	}
}

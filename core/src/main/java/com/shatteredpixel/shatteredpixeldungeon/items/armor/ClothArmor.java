

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.护甲修理工具包;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ClothArmor extends Armor {

	{
		image = 物品表.ARMOR_CLOTH;
		
		遗产= false; //Finding them in bones would be semi-frequent and disappointing.
	}
	
	public ClothArmor() {
		super( 1 );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{Food.class,
					护甲修理工具包.class,};
			inQuantity = new int[]{1,1,};

			cost = 2;

			output = ClothArmor.class;
			outQuantity = 1;
		}

	}
}

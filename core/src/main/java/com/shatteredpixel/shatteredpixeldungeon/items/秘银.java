

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 秘银 extends Item {
	
	{
		image = 物品表.秘银;
		
		可堆叠= true;
		特别= true;
		炼金全放=true;
		物品 = true;
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{LiquidMetal.class,};
			inQuantity = new int[]{3,};

			cost = 5;

			output = 秘银.class;
			outQuantity = 1;
		}

	}
}

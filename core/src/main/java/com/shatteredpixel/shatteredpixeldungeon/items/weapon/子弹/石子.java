

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.石头;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 石子 extends 弹药{

	{
		image = 物品表.石子;

	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{石头.class,};
			inQuantity = new int[]{1,};

			cost = 1;

			output = 石子.class;
			outQuantity = 5;
		}

	}

}

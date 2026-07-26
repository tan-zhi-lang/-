

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 钻石镐 extends 镐子 {
	
	{
		image = 物品表.钻石镐;

		特别= true;
		蓝色=true;

		物品 = false;
		嬗变= true;

		tier = 5;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{镐子.class,
					木棍.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 钻石镐.class;
			outQuantity = 1;
		}

	}
}



package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 精纯药液 extends Item {

	{
		image = 物品表.精纯药液;
		
		可堆叠= true;
		炼金全放=true;
		白色 = true;
		物品 = true;

		遗产= true;
	}

	@Override
	public int 金币() {
		return 15*数量();
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{Potion.PlaceHolder.class,
					Plant.Seed.PlaceHolder.class,};
			inQuantity = new int[]{1,1};

			cost = 1;

			output = 精纯药液.class;
			outQuantity = 1;
		}

	}
}

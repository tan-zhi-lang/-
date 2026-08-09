

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 激泥酞酶 extends Item {

	{
		image = 物品表.激泥酞酶;
		
		可堆叠= true;
		炼金全放=true;
		黄色 = true;
		物品 = true;

		遗产= true;
	}

	@Override
	public int 金币() {
		return 15*数量();
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{Scroll.PlaceHolder.class,
					Runestone.PlaceHolder.class,};
			inQuantity = new int[]{1,1};

			cost = 1;

			output = 激泥酞酶.class;
			outQuantity = 1;
		}

	}
}

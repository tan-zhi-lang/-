

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

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

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) return false;

			if (ingredients.get(0) instanceof Scroll&&ingredients.get(1) instanceof Runestone){
				return true;
			}

			if (ingredients.get(0) instanceof Runestone&&ingredients.get(1) instanceof Scroll){
				return true;
			}


			return false;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 1;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);
			Scroll w = (Scroll)ingredients.get(0);

			result.数量(resinQuantity(w));
			w.数量0();

			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			Scroll s = (Scroll)ingredients.get(0);

			return new 激泥酞酶().数量(resinQuantity(s));
		}

		private int resinQuantity(Scroll s){
			return s.quantity;
		}
	}

}

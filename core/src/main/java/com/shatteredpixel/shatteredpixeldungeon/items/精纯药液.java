

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

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

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) return false;

			if (ingredients.get(0) instanceof Potion&&ingredients.get(1) instanceof Plant.Seed){
				return true;
			}
			if (ingredients.get(0) instanceof Plant.Seed&&ingredients.get(1) instanceof Potion){
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
			Potion w = (Potion)ingredients.get(0);

			result.数量(resinQuantity(w));
			w.数量0();

			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			Potion s = (Potion)ingredients.get(0);

			return new 精纯药液().数量(resinQuantity(s));
		}

		private int resinQuantity(Potion s){
			return s.quantity;
		}
	}

}

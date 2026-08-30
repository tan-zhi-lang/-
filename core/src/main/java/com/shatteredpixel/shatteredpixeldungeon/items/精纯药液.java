

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
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

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			boolean potion = false;
			boolean seed = false;

			for (Item i : ingredients){
				if (i instanceof Plant.Seed) {
					seed = true;
					//if it is a regular or exotic potion
				} else if (ExoticPotion.regToExo.containsKey(i.getClass())
						   ||ExoticPotion.regToExo.containsValue(i.getClass())) {
					potion = true;
				}
			}

			return potion && seed;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 1;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {

			for (Item i : ingredients){
				i.数量减();
			}

			return sampleOutput(null);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new 精纯药液();
		}
	}
}

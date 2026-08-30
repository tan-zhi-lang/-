

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
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
			boolean scroll = false;
			boolean stone = false;

			for (Item i : ingredients){
				if (i instanceof Runestone){
					stone = true;
					//if it is a regular or exotic potion
				} else if (ExoticScroll.regToExo.containsKey(i.getClass())
						   ||ExoticScroll.regToExo.containsValue(i.getClass())) {
					scroll = true;
				}
			}

			return scroll && stone;
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
			return new 激泥酞酶();
		}
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class StewedMeat extends Food {
	
	{
		image = 物品表.STEWED;
		energy = Hunger.HUNGRY/2f;
		炼金全放 = true;
	}
	
	@Override
	public int 金币() {
		return 8 * quantity;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			return ingredients.size() == 1
					&& ingredients.get(0) instanceof MysteryMeat;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			MysteryMeat m = (MysteryMeat) ingredients.get(0);
			if(m!=null){
				return metalQuantity(m);
			}
			return 1;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);
			MysteryMeat m = (MysteryMeat) ingredients.get(0);
			result.get数量(metalQuantity(m));
			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			MysteryMeat m = (MysteryMeat) ingredients.get(0);

				return new StewedMeat().get数量(metalQuantity(m));
		}

		private int metalQuantity(MysteryMeat m){
			int quantity = m.get数量();
			return quantity;
		}
	}

}

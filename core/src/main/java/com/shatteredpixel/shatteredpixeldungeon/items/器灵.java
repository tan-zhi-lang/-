

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class 器灵 extends Item {

	{
		image = 物品表.器灵;
		
		可堆叠= true;
		炼金全放=true;
		蓝色 = true;
		物品 = true;

		遗产= true;
	}

	@Override
	public int 金币() {
		return 25*数量();
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 1) return false;

			if (ingredients.get(0) instanceof Weapon w&&w.tier()>=5){
				return true;
			}


			return false;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 5;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);
			Weapon w = (Weapon)ingredients.get(0);

			result.数量(resinQuantity(w));
			w.数量0();

			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			Weapon s = (Weapon)ingredients.get(0);

			return new 器灵().数量(resinQuantity(s));
		}

		private int resinQuantity(Weapon s){
			return s.quantity;
		}
	}

}

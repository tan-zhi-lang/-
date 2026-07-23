

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.嬗变卷轴;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class 箱引菱晶 extends Spell {

	{
		image = 物品表.箱引菱晶;

		icon = 物品表.Icons.箱引;
		talentChance = 1/(float)Recipe.OUT_QUANTITY;
	}


	@Override
	protected void onCast(Hero hero) {

		int x=1;
		Item item;
		while(x<=6){
			x++;
			do{
				item = Generator.random();
			}while(item instanceof Gold);
			item.放背包();
		}
		detach(Dungeon.hero.belongings.backpack);
	}

	@Override
	public int 金币() {
		//lower value, as it's very cheap to make (and also sold at shops)
		return (int)(20 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	@Override
	public int 能量() {
		return (int)(4 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		private static final int OUT_QUANTITY = 2;

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) return false;

			if (ingredients.get(0) instanceof 传送卷轴&&ingredients.get(1) instanceof 嬗变卷轴){
				return true;
			}

			if (ingredients.get(0) instanceof 嬗变卷轴&&ingredients.get(1) instanceof 传送卷轴){
				return true;
			}


			return false;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 15;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			ingredients.get(0).数量减();
			ingredients.get(1).数量减();
			return sampleOutput(null);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new 箱引菱晶().数量(OUT_QUANTITY);
		}
	}

}

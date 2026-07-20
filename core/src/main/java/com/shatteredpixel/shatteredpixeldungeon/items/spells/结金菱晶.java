

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.激泥酞酶;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;

import java.util.ArrayList;

public class 结金菱晶 extends Spell {

	{
		image = 物品表.结金菱晶;

		icon = 物品表.Icons.结金;
		talentChance = 1/(float)Recipe.OUT_QUANTITY;
	}

	private static WndBag parentWnd;

	@Override
	protected void onCast(Hero hero) {
		parentWnd = GameScene.selectItem( itemSelector );
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

		private static final int OUT_QUANTITY = 3;

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) return false;

			if (ingredients.get(0) instanceof TelekineticGrab && ingredients.get(1) instanceof 激泥酞酶){
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
			ingredients.get(0).数量减();
			ingredients.get(1).数量减();
			return sampleOutput(null);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new 结金菱晶().数量(OUT_QUANTITY);
		}
	}

	private static WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {
		@Override
		public String textPrompt() {
			return Messages.get(结金菱晶.class,"prompt");
		}

		@Override
		public boolean itemSelectable(Item item) {
			return !(item instanceof 结金菱晶)
					&& Shopkeeper.canSell(item);
		}

		@Override
		public void onSelect( Item item ) {
			if (item != null) {
				if (parentWnd != null) {
					parentWnd = GameScene.selectItem(itemSelector);
				}
				Dungeon.gold(item.金币提升()*20);
				item.detachAll();

			}
		}
	};


}

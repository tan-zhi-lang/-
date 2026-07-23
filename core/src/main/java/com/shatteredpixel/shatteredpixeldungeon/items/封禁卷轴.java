

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;

public class 封禁卷轴 extends 用品 {
	
	
	{
		image = 物品表.封禁卷轴;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{升级卷轴.class,};
			inQuantity = new int[]{1,};

			cost = 10;

			output = 封禁卷轴.class;
			outQuantity = 1;
		}

	}
	@Override
	public void 使用(Hero hero){
		GameScene.selectItem(itemSelector);
		super.使用(hero);
	}
	protected Class<?extends Bag> preferredBag = Belongings.Backpack.class;

	protected boolean usableOnItem(Item item) {
		return item.可升级();
	}

	protected void onItemSelected(Item item) {
		item.额外升级(item.等级()/5+1);
		item.封禁升级=true;
	}
	public WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(升级卷轴.class,"inv_title");
		}

		@Override
		public Class<? extends Bag> preferredBag() {
			return preferredBag;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return usableOnItem(item);
		}

		@Override
		public void onSelect( Item item ) {

			if (item != null){
				onItemSelected(item);
			}
		}
	};

}

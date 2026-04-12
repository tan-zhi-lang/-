package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.食物栏标;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.utils.Bundle;

public class 食物栏 extends Buff  implements 食物栏标.Action{

	public Food food= null;
	private static final String FOOD =        "food";

	@Override
	public boolean attachTo(Char target){
		食物栏标.setAction(this);
		BuffIndicator.refreshHero();
		return super.attachTo(target);
	}


	@Override
	public boolean act(){
		if(Dungeon.符文("备用口粮")&&算法.概率学(1)&&food==null){
			food=new Food();
			食物栏标.setAction(this);
			BuffIndicator.refreshHero();
		}

		spend( TICK );

		return true;
	}
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(FOOD,food);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		food= (Food) bundle.get(FOOD);
		食物栏标.setAction(this);
		BuffIndicator.refreshHero();

	}
	@Override
	public String actionName(){
		return name();
	}

	@Override
	public Visual primaryVisual() {
		Image ico;
		if(food!=null)
		ico= new ItemSprite(food);
		else
		ico= new ItemSprite(物品表.FOOD_HOLDER);
		return ico;
	}

	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		if(food!=null)
		txt.text(""+food.数量());
		else return null;
		txt.hardlight(CharSprite.增强);
		txt.measure();
		return txt;
	}
	@Override
	public int indicatorColor() {
		return 0x44FF44;
	}

	@Override
	public void doAction(){
		if(food!=null){
			food.数量减();
			food.execute((Hero)target,food.AC_食物栏);
			if(food.数量()<=0){
				food=null;
			}
			食物栏标.setAction(食物栏.this);
			BuffIndicator.refreshHero();
		}
		else
		GameScene.selectItem( itemSelector );
	}

	@Override
	public void doAction2(){
		if(food!=null){
			food.放背包();
			food=null;
			食物栏标.setAction(食物栏.this);
			BuffIndicator.refreshHero();
		}
	}
	private WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {
		@Override
		public String textPrompt() {
			return Messages.get(食物栏.class,"sell");
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Food;
		}

		@Override
		public void onSelect( Item item ) {
			if(item instanceof Food f){
				f.detachAll();

				food=f;

				食物栏标.setAction(食物栏.this);
				BuffIndicator.refreshHero();
			}
		}
	};
}
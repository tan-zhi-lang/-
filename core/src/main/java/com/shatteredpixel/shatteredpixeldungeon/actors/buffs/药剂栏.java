package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.药剂栏标;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.utils.Bundle;

public class 药剂栏 extends Buff  implements 药剂栏标.Action{

	public Potion potion= null;
	private static final String POTION =        "potion";

	@Override
	public boolean attachTo(Char target){
		药剂栏标.setAction(this);
		BuffIndicator.refreshHero();
		return super.attachTo(target);
	}

	@Override
	public boolean act(){
		if( Dungeon.符文("备用治疗药剂")&&算法.概率学(1)&&potion==null){
			potion=new 治疗药剂();
			药剂栏标.setAction(this);
			BuffIndicator.refreshHero();
		}

			spend( TICK );

		return true;
	}

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(POTION,potion);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		potion= (Potion) bundle.get(POTION);
		药剂栏标.setAction(this);
		BuffIndicator.refreshHero();

	}
	@Override
	public String actionName(){
		return name();
	}

	@Override
	public Visual primaryVisual() {
		Image ico;
		if(potion!=null)
		ico= new ItemSprite(potion);
		else
		ico= new ItemSprite(物品表.POTION_HOLDER);
		return ico;
	}

	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		if(potion!=null)
		txt.text(""+potion.数量());
		else return null;
		txt.hardlight(CharSprite.增强绿);
		txt.measure();
		return txt;
	}
	@Override
	public int indicatorColor() {
		return 0xFF4444;
	}

	@Override
	public void doAction(){
		if(potion!=null){
			potion.数量减();
			potion.药剂栏((Hero)target);
			if(potion.数量()<=0){
				potion=null;
			}
			药剂栏标.setAction(药剂栏.this);
			BuffIndicator.refreshHero();
		}
		else
		GameScene.selectItem( itemSelector );
	}
	@Override
	public void doAction2(){
		if(potion!=null){
			potion.放背包();
			potion=null;
			药剂栏标.setAction(药剂栏.this);
			BuffIndicator.refreshHero();
		}
	}
	private WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {
		@Override
		public String textPrompt() {
			return Messages.get(药剂栏.class,"sell");
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Potion;
		}

		@Override
		public void onSelect( Item item ) {
			if(item instanceof Potion p){
				p.detachAll();

				potion=p;

				药剂栏标.setAction(药剂栏.this);
				BuffIndicator.refreshHero();
			}
		}
	};
}
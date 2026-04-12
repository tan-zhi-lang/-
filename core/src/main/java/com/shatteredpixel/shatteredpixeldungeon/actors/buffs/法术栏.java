package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.UnstableSpellbook;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.法术栏标;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.utils.Bundle;

public class 法术栏 extends Buff  implements 法术栏标.Action{

	public static Item item= null;
	private static final String ITEM=        "item";

	@Override
	public boolean attachTo(Char target){
		法术栏标.setAction(this);
		BuffIndicator.refreshHero();
		return super.attachTo(target);
	}

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(ITEM,item);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		item= (Item) bundle.get(ITEM);
		法术栏标.setAction(this);
		BuffIndicator.refreshHero();

	}
	@Override
	public String actionName(){
		return name();
	}

	@Override
	public int actionIcon(){
		return HeroIcon.NONE;
	}

	@Override
	public Visual primaryVisual() {
		Image ico;
		if(item!=null)
		ico= new ItemSprite(item);
		else
		ico= new ItemSprite(物品表.POTION_HOLDER);
		return ico;
	}

	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		if(item!=null)
		txt.text(""+item.数量());
		else return null;
		txt.hardlight(CharSprite.增强);
		txt.measure();
		return txt;
	}
	@Override
	public int indicatorColor() {
		return 0xFF4444;
	}

	@Override
	public void doAction(){
		if(item instanceof Scroll s){

			if (target.buff(MagicImmune.class) != null){
				GLog.w(Messages.get(Scroll.class,"no_magic"));
			} else if (target.buff( Blindness.class )!=null) {
				GLog.w( Messages.get(Scroll.class, "blinded") );
			} else if (target.buff(UnstableSpellbook.bookRecharge.class)!=null
					   && target.buff(UnstableSpellbook.bookRecharge.class).isCursed()
					   && !(s instanceof 祛邪卷轴||s instanceof ScrollOfAntiMagic)){
				GLog.n( Messages.get(Scroll.class, "cursed") );
			} else {
				s.数量减();
				s.doRead();;
				if(s.数量()<=0){
					item=null;
				}
				法术栏标.setAction(法术栏.this);
				BuffIndicator.refreshHero();
			}
		}
		else
		GameScene.selectItem( itemSelector );
	}
	private WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {
		@Override
		public String textPrompt() {
			return Messages.get(法术栏.class,"sell");
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Scroll;
		}

		@Override
		public void onSelect( Item item ){
			if(item!=null){
				if(item instanceof Scroll p){
					p.detachAll();

					法术栏.item=p;
				}

				法术栏标.setAction(法术栏.this);
				BuffIndicator.refreshHero();
			}
		}
	};
}


package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoItem;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTradeItem;

import java.util.ArrayList;

public class 商人信标 extends Item {
	
	
	public static final String AC_出售	= "出售";
	public static final String AC_单发抽	= "单发抽";
	public static final String AC_十连抽	= "十连抽";
	
	{
		image = 物品表.BEACON;
		物品 = true;
		
		defaultAction = AC_出售;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_出售 );
		actions.add( AC_单发抽 );
		actions.add( AC_十连抽 );
		return actions;
	}
	
	private static WndBag parentWnd;
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );
		
		if (action.equals( AC_出售 )) {
			hero.spend();
			hero.busy();
			hero.sprite.operate( hero.pos );
			
			parentWnd = GameScene.selectItem(itemSelector);
		}
		if (action.equals( AC_单发抽 )) {
			hero.spend();
			hero.busy();
			hero.sprite.operate( hero.pos );
			Item item;
			if(Dungeon.gold>=Dungeon.depth*25){
				Dungeon.gold(-Dungeon.depth*25);
				do{
					item = Generator.random();
					item.放背包();
				}while(item instanceof Gold);
			}else{
				Messages.get(this, "nogold");
			}
		}
		if (action.equals( AC_十连抽 )) {
			hero.spend();
			hero.busy();
			hero.sprite.operate( hero.pos );
			if(Dungeon.gold>=Dungeon.depth*9*25){
				Dungeon.gold(-Dungeon.depth*9*25);
				int x=1;
				Item item;
				while(x<=10){
					x++;
					do{
						item = Generator.random();
						item.放背包();
					}while(item instanceof Gold);
				}
			}else{
				Messages.get(this, "nogold");
			}
		}
	}
	
	private static WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {
		@Override
		public String textPrompt() {
			return Messages.get(this, "sell");
		}
		
		@Override
		public boolean itemSelectable(Item item) {
			return Shopkeeper.canSell(item);
		}
		
		@Override
		public void onSelect( Item item ) {
			if (item != null) {
				if (parentWnd != null) {
					parentWnd = GameScene.selectItem(itemSelector);
				}
				GameScene.show( new WndAlchemizeItem(item,parentWnd ));
			}
		}
	};
	
	public static class WndAlchemizeItem extends WndInfoItem{
		
		private static final float GAP		= 2;
		private static final int BTN_HEIGHT	= 18;
		
		private WndBag owner;
		
		public WndAlchemizeItem(Item item, WndBag owner) {
			super(item);
			
			this.owner = owner;
			
			float pos = height;
			
			if (Shopkeeper.canSell(item)) {
				if (item.数量()==1) {
					
					RedButton
							btnSell = new RedButton(Messages.get(this,"sell",item.金币())) {
						@Override
						protected void onClick() {
							WndTradeItem.sell(item);
							hide();
							consumeAlchemize();
						}
					};
					btnSell.setRect(0, pos + GAP, width, BTN_HEIGHT);
					btnSell.icon(new ItemSprite(物品表.GOLD));
					add(btnSell);
					
					pos = btnSell.bottom();
					
				} else {
					
					int priceAll = item.金币();
					RedButton btnSell1 = new RedButton(Messages.get(this, "sell_1", priceAll / item.数量())) {
						@Override
						protected void onClick() {
							WndTradeItem.sellOne(item);
							hide();
							consumeAlchemize();
						}
					};
					btnSell1.setRect(0, pos + GAP, width, BTN_HEIGHT);
					btnSell1.icon(new ItemSprite(物品表.GOLD));
					add(btnSell1);
					RedButton btnSellAll = new RedButton(Messages.get(this, "sell_all", priceAll)) {
						@Override
						protected void onClick() {
							WndTradeItem.sell(item);
							hide();
							consumeAlchemize();
						}
					};
					btnSellAll.setRect(0, btnSell1.bottom() + 1, width, BTN_HEIGHT);
					btnSellAll.icon(new ItemSprite(物品表.GOLD));
					add(btnSellAll);
					
					pos = btnSellAll.bottom();
					
				}
			}
			
			resize( width, (int)pos );
			
		}
		
		private void consumeAlchemize(){
			if (curItem.数量()<=1){
				curItem.detachAll(Dungeon.hero.belongings.backpack);
				if (owner != null) {
					owner.hide();
				}
			} else {
				curItem.detach(Dungeon.hero.belongings.backpack);
				if (owner != null){
					owner.hide();
				}
				GameScene.selectItem(itemSelector);
			}
		}
		
	}
}

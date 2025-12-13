

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.ItemButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.windows.IconTitle;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoItem;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 空间之戒 extends Item {
	
	
	public static final String AC_使用	= "使用";
	
	{
		image = 物品表.空间之戒;
		特别 = true;
		物品 = true;
		可以空间=false;
		
		defaultAction = AC_使用;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_使用 );
		return actions;
	}
	
	private Item item= null;
	private Item item2= null;
	private Item item3= null;
	private Item item4= null;
	private Item item5= null;
	private Item item6= null;
	private Item item7= null;
	private Item item8= null;
	private Item item9= null;
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );
		
		if (action.equals( AC_使用 )) {
			hero.spend();
			hero.busy();
			hero.sprite.operate( hero.pos );
			
			GameScene.show(new Wnd());
		}
	}
	
	
	private static final String ITEM=        "item";
	private static final String ITEM2=        "item2";
	private static final String ITEM3=        "item3";
	private static final String ITEM4=        "item4";
	private static final String ITEM5=        "item5";
	private static final String ITEM6=        "item6";
	private static final String ITEM7=        "item7";
	private static final String ITEM8=        "item8";
	private static final String ITEM9=        "item9";
	
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		
		if (item!=null) bundle.put(ITEM,item);
		if (item2!=null) bundle.put(ITEM2,item2);
		if (item3!=null) bundle.put(ITEM3,item3);
		if (item4!=null) bundle.put(ITEM4,item4);
		if (item5!=null) bundle.put(ITEM5,item5);
		if (item6!=null) bundle.put(ITEM6,item6);
		if (item7!=null) bundle.put(ITEM7,item7);
		if (item8!=null) bundle.put(ITEM8,item8);
		if (item9!=null) bundle.put(ITEM9,item9);
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(ITEM)) item= (Item)bundle.get(ITEM);
		if (bundle.contains(ITEM2)) item2= (Item)bundle.get(ITEM2);
		if (bundle.contains(ITEM3)) item3= (Item)bundle.get(ITEM3);
		if (bundle.contains(ITEM4)) item4= (Item)bundle.get(ITEM4);
		if (bundle.contains(ITEM5)) item5= (Item)bundle.get(ITEM5);
		if (bundle.contains(ITEM6)) item6= (Item)bundle.get(ITEM6);
		if (bundle.contains(ITEM7)) item7= (Item)bundle.get(ITEM7);
		if (bundle.contains(ITEM8)) item8= (Item)bundle.get(ITEM8);
		if (bundle.contains(ITEM9)) item9= (Item)bundle.get(ITEM9);
	}
	
	private class Wnd extends Window{
		
		private static final int BTN_SIZE	= 32;
		private static final float GAP		= 2;
		private static final float BTN_GAP	= 12;
		private static final int WIDTH		= 112;
		
		private ItemButton btnitem;
		private ItemButton btnitem2;
		private ItemButton btnitem3;
		private ItemButton btnitem4;
		private ItemButton btnitem5;
		private ItemButton btnitem6;
		private ItemButton btnitem7;
		private ItemButton btnitem8;
		private ItemButton btnitem9;
		
		Wnd(){
			
			IconTitle titlebar = new IconTitle();
			titlebar.icon( new ItemSprite(new 空间之戒()));
			titlebar.label(Messages.get(this,"title"));
			titlebar.setRect( 0, 0, WIDTH, 0 );
			add( titlebar );
			
			RenderedTextBlock message =
					PixelScene.renderTextBlock(Messages.get(this,"desc"),6);
			message.maxWidth( WIDTH );
			message.setPos(0, titlebar.bottom() + GAP);
			add( message );
			
			//region 1
			btnitem= new ItemButton(){
				@Override
				protected void onClick() {
					if (item!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item,Dungeon.hero.pos);
						}
						item= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return item.可以空间;
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero,false,false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item= item;
								item(item);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem.setRect(GAP/2+3,message.top()+message.height()+GAP*4,BTN_SIZE,BTN_SIZE);
			if (item!=null) {
				btnitem.item(item);
			} else {
				btnitem.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem);
			//endregion
			
			//region 2
			btnitem2= new ItemButton(){
				@Override
				protected void onClick() {
					if (item2!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item2.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item2,Dungeon.hero.pos);
						}
						item2= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return item.可以空间;
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item2= item;
								item(item2);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem2.setRect( btnitem.right() + BTN_GAP/3, btnitem.top(), BTN_SIZE, BTN_SIZE );
			if (item2!=null) {
				btnitem2.item(item2);
			} else {
				btnitem2.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem2);
			//endregion
			
			//region 3
			btnitem3= new ItemButton(){
				@Override
				protected void onClick() {
					if (item3!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item3.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item3,Dungeon.hero.pos);
						}
						item3= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return item.可以空间;
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item3= item;
								item(item3);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem3.setRect( btnitem2.right() + BTN_GAP/3, btnitem2.top(), BTN_SIZE, BTN_SIZE );
			if (item3!=null) {
				btnitem3.item(item3);
			} else {
				btnitem3.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem3);
			//endregion
			
			
			//region 4
			btnitem4= new ItemButton(){
				@Override
				protected void onClick() {
					if (item4!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item4.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item4,Dungeon.hero.pos);
						}
						item4= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return item.可以空间;
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item4= item;
								item(item4);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem4.setRect(GAP/2+3,message.top()+message.height()+GAP*6+btnitem.height(),BTN_SIZE,BTN_SIZE);
			if (item4!=null) {
				btnitem4.item(item4);
			} else {
				btnitem4.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem4);
			//endregion
			
			//region 5
			btnitem5= new ItemButton(){
				@Override
				protected void onClick() {
					if (item5!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item5.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item5,Dungeon.hero.pos);
						}
						item5= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return item.可以空间;
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item5= item;
								item(item5);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem5.setRect( btnitem4.right() + BTN_GAP/3, btnitem4.top(), BTN_SIZE, BTN_SIZE );
			if (item5!=null) {
				btnitem5.item(item5);
			} else {
				btnitem5.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem5);
			//endregion
			
			//region 6
			btnitem6= new ItemButton(){
				@Override
				protected void onClick() {
					if (item6!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item6.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item6,Dungeon.hero.pos);
						}
						item6= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return item.可以空间;
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item6= item;
								item(item6);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem6.setRect( btnitem5.right() + BTN_GAP/3, btnitem4.top(), BTN_SIZE, BTN_SIZE );
			if (item6!=null) {
				btnitem6.item(item6);
			} else {
				btnitem6.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem6);
			//endregion
			
			//region 7
			btnitem7= new ItemButton(){
				@Override
				protected void onClick() {
					if (item7!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item7.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item7,Dungeon.hero.pos);
						}
						item7= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return item.可以空间;
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item7= item;
								item(item7);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem7.setRect(GAP/2+3,message.top()+message.height()+GAP*8+btnitem4.height()*2,BTN_SIZE,BTN_SIZE);
			if (item7!=null) {
				btnitem7.item(item7);
			} else {
				btnitem7.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem7);
			//endregion
			
			//region 8
			btnitem8= new ItemButton(){
				@Override
				protected void onClick() {
					if (item8!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item8.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item8,Dungeon.hero.pos);
						}
						item8= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 空间之戒||item instanceof Bag||item instanceof Bag);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item8= item;
								item(item8);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem8.setRect( btnitem4.right() + BTN_GAP/3, btnitem7.top(), BTN_SIZE, BTN_SIZE );
			if (item5!=null) {
				btnitem8.item(item5);
			} else {
				btnitem8.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem8);
			//endregion
			
			//region 9
			btnitem9= new ItemButton(){
				@Override
				protected void onClick() {
					if (item9!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!item9.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(item9,Dungeon.hero.pos);
						}
						item9= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(空间之戒.Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return item.可以空间;
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								item9= item;
								item(item9);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem9.setRect( btnitem8.right() + BTN_GAP/3, btnitem7.top(), BTN_SIZE, BTN_SIZE );
			if (item9!=null) {
				btnitem9.item(item9);
			} else {
				btnitem9.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem9);
			//endregion
			
			resize(WIDTH, (int)(btnitem9.bottom() + GAP*2));
		}
		
	}
}

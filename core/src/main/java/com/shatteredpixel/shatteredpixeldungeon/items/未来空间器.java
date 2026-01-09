

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
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

import java.util.ArrayList;

public class 未来空间器 extends Item {
	
	
	public static final String AC_使用	= "使用";
	
	{
		image = 物品表.未来空间器;
		特别 = true;
		物品 = true;
		嬗变= false;
		可以空间=false;
		
		defaultAction = AC_使用;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_使用 );
		return actions;
	}
	
	private static WndBag parentWnd;
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
	private static class Wnd extends Window{
		
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
			titlebar.icon( new ItemSprite(new 未来空间器()));
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
					if (Statistics.item!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item,Dungeon.hero.pos);
						}
						Statistics.item= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item= item;
									item(Statistics.item);
								}
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
			if (Statistics.item!=null) {
				btnitem.item(Statistics.item);
			} else {
				btnitem.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem);
			//endregion
			
			//region 2
			btnitem2= new ItemButton(){
				@Override
				protected void onClick() {
					if (Statistics.item2!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item2.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item2,Dungeon.hero.pos);
						}
						Statistics.item2= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item2= item;
									item(Statistics.item2);
								}
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
			if (Statistics.item2!=null) {
				btnitem2.item(Statistics.item2);
			} else {
				btnitem2.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem2);
			//endregion
			
			//region 3
			btnitem3= new ItemButton(){
				@Override
				protected void onClick() {
					if (Statistics.item3!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item3.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item3,Dungeon.hero.pos);
						}
						Statistics.item3= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item3= item;
									item(Statistics.item3);
								}
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
			if (Statistics.item3!=null) {
				btnitem3.item(Statistics.item3);
			} else {
				btnitem3.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem3);
			//endregion
			
			
			//region 4
			btnitem4= new ItemButton(){
				@Override
				protected void onClick() {
					if (Statistics.item4!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item4.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item4,Dungeon.hero.pos);
						}
						Statistics.item4= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item4= item;
									item(Statistics.item4);
								}
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
			if (Statistics.item4!=null) {
				btnitem4.item(Statistics.item4);
			} else {
				btnitem4.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem4);
			//endregion
			
			//region 5
			btnitem5= new ItemButton(){
				@Override
				protected void onClick() {
					if (Statistics.item5!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item5.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item5,Dungeon.hero.pos);
						}
						Statistics.item5= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item5= item;
									item(Statistics.item5);
								}
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
			if (Statistics.item5!=null) {
				btnitem5.item(Statistics.item5);
			} else {
				btnitem5.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem5);
			//endregion
			
			//region 6
			btnitem6= new ItemButton(){
				@Override
				protected void onClick() {
					if (Statistics.item6!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item6.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item6,Dungeon.hero.pos);
						}
						Statistics.item6= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item6= item;
									item(Statistics.item6);
								}
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
			if (Statistics.item6!=null) {
				btnitem6.item(Statistics.item6);
			} else {
				btnitem6.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem6);
			//endregion
			
			//region 7
			btnitem7= new ItemButton(){
				@Override
				protected void onClick() {
					if (Statistics.item7!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item7.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item7,Dungeon.hero.pos);
						}
						Statistics.item7= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item7= item;
									item(Statistics.item7);
								}
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
			if (Statistics.item7!=null) {
				btnitem7.item(Statistics.item7);
			} else {
				btnitem7.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem7);
			//endregion
			
			//region 8
			btnitem8= new ItemButton(){
				@Override
				protected void onClick() {
					if (Statistics.item8!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item8.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item8,Dungeon.hero.pos);
						}
						Statistics.item8= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item8= item;
									item(Statistics.item8);
								}
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
			if (Statistics.item5!=null) {
				btnitem8.item(Statistics.item5);
			} else {
				btnitem8.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem8);
			//endregion
			
			//region 9
			btnitem9= new ItemButton(){
				@Override
				protected void onClick() {
					if (Statistics.item9!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Statistics.item9.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Statistics.item9,Dungeon.hero.pos);
						}
						Statistics.item9= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(未来空间器.Wnd.class,"item_prompt");
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
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
									Statistics.item9= item;
									item(Statistics.item9);
								}
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
			if (Statistics.item9!=null) {
				btnitem9.item(Statistics.item9);
			} else {
				btnitem9.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem9);
			//endregion
			
			resize(WIDTH, (int)(btnitem9.bottom() + GAP*2));
		}
		
	}
}

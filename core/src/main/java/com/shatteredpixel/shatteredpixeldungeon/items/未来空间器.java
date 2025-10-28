

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Bones;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.召唤物品;
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

import java.util.ArrayList;

public class 未来空间器 extends Item {
	
	
	public static final String AC_使用	= "使用";
	
	{
		image = 物品表.空间之戒;
		物品 = true;
		
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
					if (Bones.item!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item,Dungeon.hero.pos);
						}
						Bones.item= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero,false,false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item= item;
								item(Bones.item);
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
			if (Bones.item!=null) {
				btnitem.item(Bones.item);
			} else {
				btnitem.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem);
			//endregion
			
			//region 2
			btnitem2= new ItemButton(){
				@Override
				protected void onClick() {
					if (Bones.item2!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item2.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item2,Dungeon.hero.pos);
						}
						Bones.item2= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item2= item;
								item(Bones.item2);
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
			if (Bones.item2!=null) {
				btnitem2.item(Bones.item2);
			} else {
				btnitem2.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem2);
			//endregion
			
			//region 3
			btnitem3= new ItemButton(){
				@Override
				protected void onClick() {
					if (Bones.item3!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item3.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item3,Dungeon.hero.pos);
						}
						Bones.item3= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item3= item;
								item(Bones.item3);
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
			if (Bones.item3!=null) {
				btnitem3.item(Bones.item3);
			} else {
				btnitem3.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem3);
			//endregion
			
			
			//region 4
			btnitem4= new ItemButton(){
				@Override
				protected void onClick() {
					if (Bones.item4!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item4.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item4,Dungeon.hero.pos);
						}
						Bones.item4= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item4= item;
								item(Bones.item4);
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
			if (Bones.item4!=null) {
				btnitem4.item(Bones.item4);
			} else {
				btnitem4.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem4);
			//endregion
			
			//region 5
			btnitem5= new ItemButton(){
				@Override
				protected void onClick() {
					if (Bones.item5!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item5.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item5,Dungeon.hero.pos);
						}
						Bones.item5= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item5= item;
								item(Bones.item5);
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
			if (Bones.item5!=null) {
				btnitem5.item(Bones.item5);
			} else {
				btnitem5.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem5);
			//endregion
			
			//region 6
			btnitem6= new ItemButton(){
				@Override
				protected void onClick() {
					if (Bones.item6!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item6.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item6,Dungeon.hero.pos);
						}
						Bones.item6= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item6= item;
								item(Bones.item6);
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
			if (Bones.item6!=null) {
				btnitem6.item(Bones.item6);
			} else {
				btnitem6.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem6);
			//endregion
			
			//region 7
			btnitem7= new ItemButton(){
				@Override
				protected void onClick() {
					if (Bones.item7!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item7.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item7,Dungeon.hero.pos);
						}
						Bones.item7= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item7= item;
								item(Bones.item7);
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
			if (Bones.item7!=null) {
				btnitem7.item(Bones.item7);
			} else {
				btnitem7.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem7);
			//endregion
			
			//region 8
			btnitem8= new ItemButton(){
				@Override
				protected void onClick() {
					if (Bones.item8!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item8.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item8,Dungeon.hero.pos);
						}
						Bones.item8= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item8= item;
								item(Bones.item8);
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
			if (Bones.item5!=null) {
				btnitem8.item(Bones.item5);
			} else {
				btnitem8.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem8);
			//endregion
			
			//region 9
			btnitem9= new ItemButton(){
				@Override
				protected void onClick() {
					if (Bones.item9!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!Bones.item9.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(Bones.item9,Dungeon.hero.pos);
						}
						Bones.item9= null;
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
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								if (item!=null&&item.isEquipped(Dungeon.hero)){
									((Weapon) item).doUnequip(Dungeon.hero, false, false);
								} else {
									item.detach(Dungeon.hero.belongings.backpack);
								}
								Bones.item9= item;
								item(Bones.item9);
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
			if (Bones.item9!=null) {
				btnitem9.item(Bones.item9);
			} else {
				btnitem9.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem9);
			//endregion
			
			resize(WIDTH, (int)(btnitem9.bottom() + GAP*2));
		}
		
	}
}

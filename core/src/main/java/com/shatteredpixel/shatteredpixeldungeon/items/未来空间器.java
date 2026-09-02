

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.未来空间器冷却;
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
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;

import java.io.IOException;
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

	//全局存档文件：空间内物品跨存档互通的唯一数据源，不写入各存档，避免复制
	private static final String 空间存档文件 = "未来空间器.dat";

	public static void saveGlobal(){
		Bundle bundle = new Bundle();
		if (Statistics.item1!=null) bundle.put( "item1", Statistics.item1 );
		if (Statistics.item2!=null) bundle.put( "item2", Statistics.item2 );
		if (Statistics.item3!=null) bundle.put( "item3", Statistics.item3 );
		if (Statistics.item4!=null) bundle.put( "item4", Statistics.item4 );
		if (Statistics.item5!=null) bundle.put( "item5", Statistics.item5 );
		if (Statistics.item6!=null) bundle.put( "item6", Statistics.item6 );
		if (Statistics.item7!=null) bundle.put( "item7", Statistics.item7 );
		if (Statistics.item8!=null) bundle.put( "item8", Statistics.item8 );
		if (Statistics.item9!=null) bundle.put( "item9", Statistics.item9 );
		try {
			FileUtils.bundleToFile( 空间存档文件, bundle );
		} catch (IOException e) {
			ShatteredPixelDungeon.reportException(e);
		}
	}

	public static void loadGlobal(){
		try {
			Bundle bundle = FileUtils.bundleFromFile( 空间存档文件 );
			Statistics.item1 = bundle.contains("item1") ? (Item)bundle.get("item1") : null;
			Statistics.item2 = bundle.contains("item2") ? (Item)bundle.get("item2") : null;
			Statistics.item3 = bundle.contains("item3") ? (Item)bundle.get("item3") : null;
			Statistics.item4 = bundle.contains("item4") ? (Item)bundle.get("item4") : null;
			Statistics.item5 = bundle.contains("item5") ? (Item)bundle.get("item5") : null;
			Statistics.item6 = bundle.contains("item6") ? (Item)bundle.get("item6") : null;
			Statistics.item7 = bundle.contains("item7") ? (Item)bundle.get("item7") : null;
			Statistics.item8 = bundle.contains("item8") ? (Item)bundle.get("item8") : null;
			Statistics.item9 = bundle.contains("item9") ? (Item)bundle.get("item9") : null;
		} catch (IOException e) {
			//文件不存在（首次使用）：保持空槽
		}
	}

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
					if (Statistics.item1!=null){

						Statistics.item1.放背包();
						btnitem.item(new WndBag.Placeholder(物品表.ITEM));
						Statistics.item1= null;
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item1= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
									item(Statistics.item1);
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
			if (Statistics.item1!=null) {
				btnitem.item(Statistics.item1);
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

						Statistics.item2.放背包();
						btnitem2.item(new WndBag.Placeholder(物品表.ITEM));
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item2= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
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

						Statistics.item3.放背包();
						btnitem3.item(new WndBag.Placeholder(物品表.ITEM));
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item3= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
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

						Statistics.item4.放背包();
						btnitem4.item(new WndBag.Placeholder(物品表.ITEM));
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item4= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
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

						Statistics.item5.放背包();
						btnitem5.item(new WndBag.Placeholder(物品表.ITEM));
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item5= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
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

						Statistics.item6.放背包();
						btnitem6.item(new WndBag.Placeholder(物品表.ITEM));
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item6= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
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

						Statistics.item7.放背包();
						btnitem7.item(new WndBag.Placeholder(物品表.ITEM));
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item7= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
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

						Statistics.item8.放背包();
						btnitem8.item(new WndBag.Placeholder(物品表.ITEM));
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item8= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
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
			if (Statistics.item8!=null) {
				btnitem8.item(Statistics.item8);
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

						Statistics.item9.放背包();
						btnitem9.item(new WndBag.Placeholder(物品表.ITEM));
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
								return item.可以空间&&!item.isEquipped(Dungeon.hero);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.可堆叠&&Dungeon.hero.nobuff(未来空间器.class)&&Dungeon.hero.符文("真未来空间器")){
										item.数量(item.数量()*2);
										Buff.施加(Dungeon.hero,未来空间器冷却.class,1350f );
									}
								Statistics.item9= item;
								item.detachAll(Dungeon.hero.belongings.backpack);
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

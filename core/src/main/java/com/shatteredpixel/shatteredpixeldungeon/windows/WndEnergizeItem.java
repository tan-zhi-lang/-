

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.EnergyCrystal;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.Trinket;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.AlchemyScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;

public class WndEnergizeItem extends WndInfoItem {

	private static final float GAP		= 2;
	private static final int BTN_HEIGHT	= 18;

	private WndBag owner;

	public WndEnergizeItem(Item item, WndBag owner) {
		super(item);

		this.owner = owner;

		float pos = height;

		if (item.数量()==1) {

			RedButton btnEnergize = new RedButton( Messages.get(this, "energize", item.能量()) ) {
				@Override
				protected void onClick() {
					energizeAll( item );
					hide();
				}
			};
			btnEnergize.setRect( 0, pos + GAP, width, BTN_HEIGHT );
			btnEnergize.icon(new ItemSprite(物品表.ENERGY));
			add( btnEnergize );

			pos = btnEnergize.bottom();

		} else {

			int energyAll = item.能量();
			RedButton btnEnergize1 = new RedButton( Messages.get(this, "energize_1", energyAll / item.数量()) ) {
				@Override
				protected void onClick() {
					if (item instanceof Trinket){
					Game.scene().addToFront(new WndOptions(new ItemSprite(item),Messages.titleCase(item.name()),
														   Messages.get(WndEnergizeItem.class, "trinket_warn"),
														   Messages.get(WndEnergizeItem.class, "trinket_yes"),
														   Messages.get(WndEnergizeItem.class, "trinket_no")){
						
						@Override
						protected void onSelect(int index) {
							if (index == 0) {
								energizeAll(item);
							}
						}
						
						@Override
						public void hide() {
							super.hide();
							WndEnergizeItem.this.hide();
						}
					});
				} else {
					energizeAll(item);
					hide();
				}
				}
			};
			btnEnergize1.setRect( 0, pos + GAP, width, BTN_HEIGHT );
			btnEnergize1.icon(new ItemSprite(物品表.ENERGY));
			add( btnEnergize1 );
			RedButton btnEnergizeAll = new RedButton( Messages.get(this, "energize_all", energyAll ) ) {
				@Override
				protected void onClick() {
					energizeAll( item );
					hide();
				}
			};
			btnEnergizeAll.setRect( 0, btnEnergize1.bottom() + 1, width, BTN_HEIGHT );
			btnEnergizeAll.icon(new ItemSprite(物品表.ENERGY));
			add( btnEnergizeAll );

			pos = btnEnergizeAll.bottom();

		}

		resize( width, (int)pos );

	}

	@Override
	public void hide() {

		super.hide();

		if (owner != null) {
			owner.hide();
			openItemSelector();
		}
	}

	public static void energizeAll(Item item ) {

//		if (item.isEquipped( Dungeon.hero ) && !((EquipableItem)item).doUnequip( Dungeon.hero, false )) {
//			return;
//		}
		item.detachAll( Dungeon.hero.belongings.backpack );
		energize(item);
	}

	public static void energizeOne( Item item ) {

		if (item.数量()<=1) {
			energizeAll( item );
		} else {
			energize(item.detach( Dungeon.hero.belongings.backpack ));
		}
	}

	private static void energize(Item item){
		Hero hero = Dungeon.hero;

		if (ShatteredPixelDungeon.scene() instanceof AlchemyScene){

			Dungeon.energy(item.能量());
			((AlchemyScene) ShatteredPixelDungeon.scene()).createEnergy();
			if (!item.已鉴定()){
				((AlchemyScene) ShatteredPixelDungeon.scene()).showIdentify(item);
			}

		} else {

			//energizing items doesn't spend time
			hero.spend(-hero.cooldown());
			new EnergyCrystal(item.能量()).doPickUp(hero);
			item.鉴定();
			GLog.h("You energized: " + item.name());

		}
	}

	public static WndBag openItemSelector(){
		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			return GameScene.selectItem( selector );
		} else {
			WndBag window = WndBag.getBag( selector );
			ShatteredPixelDungeon.scene().addToFront(window);
			return window;
		}
	}

	public static WndBag.ItemSelector selector = new WndBag.ItemSelector() {
		@Override
		public String textPrompt() {
			return Messages.get(WndEnergizeItem.class, "prompt");
		}

		@Override
		public boolean itemSelectable(Item item) {
			if(item instanceof Bag) {
				return false;
			}
			if(item instanceof EquipableItem e&&e.isEquipped(Dungeon.hero)){
				return false;
			}
			return item.能量() > 0;
		}

		@Override
		public void onSelect(Item item) {
			if (item != null) {
				WndBag parentWnd = openItemSelector();
				if (ShatteredPixelDungeon.scene() instanceof GameScene) {
					GameScene.show(new WndEnergizeItem(item, parentWnd));
				} else {
					ShatteredPixelDungeon.scene().addToFront(new WndEnergizeItem(item, parentWnd));
				}
			}
		}
	};

}

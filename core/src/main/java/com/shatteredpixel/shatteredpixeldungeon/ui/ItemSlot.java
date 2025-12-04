

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.荆棘斗篷;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfShielding;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.手枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.utils.Rect;

public class ItemSlot extends Button {
	public static final int DEGRADED    = 0xFF4444; // 深红色
	public static final int UPGRADED    = 0x44FF44; // 亮绿色
	public static final int FADED       = 0x999999; // 灰色
	public static final int WARNING     = 0xFF8800; // 橙色
	public static final int ENHANCED    = 0x3399FF; // 蓝色
	public static final int MASTERED    = 0xFFFF44; // 黄色
	public static final int CURSE_INFUSED = 0x8800FF; // 紫色
	
	
	private static final float ENABLED	= 1.0f;
	private static final float DISABLED	= 0.3f;

	private Rect margin = new Rect();

	protected ItemSprite sprite;
	protected Item       item;
	protected BitmapText status;
	protected BitmapText extra;
	protected Image      itemIcon;
	protected BitmapText level;

	protected BitmapText center;

	private static final String TXT	= "%d";
	private static final String TXT_TYPICAL_STR	= "%d";

	private static final String TXT_LEVEL	= "+%d";

	// Special "virtual items"
	public static final Item CHEST = new Item() {
		public int image() { return 物品表.CHEST; }
		public String name() { return Messages.get(Heap.class, "chest"); }
	};
	public static final Item LOCKED_CHEST = new Item() {
		public int image() { return 物品表.LOCKED_CHEST; }
		public String name() { return Messages.get(Heap.class, "locked_chest"); }
	};
	public static final Item CRYSTAL_CHEST = new Item() {
		public int image() { return 物品表.CRYSTAL_CHEST; }
		public String name() { return Messages.get(Heap.class, "crystal_chest"); }
	};
	public static final Item TOMB = new Item() {
		public int image() { return 物品表.TOMB; }
		public String name() { return Messages.get(Heap.class, "tomb"); }
	};
	public static final Item SKELETON = new Item() {
		public int image() { return 物品表.BONES; }
		public String name() { return Messages.get(Heap.class, "skeleton"); }
	};
	public static final Item REMAINS = new Item() {
		public int image() { return 物品表.REMAINS; }
		public String name() { return Messages.get(Heap.class, "remains"); }
	};
	
	public ItemSlot() {
		super();
		sprite.visible(false);
		enable(false);
	}
	
	public ItemSlot( Item item ) {
		this();
		item( item );
	}
		
	@Override
	protected void createChildren() {
		
		super.createChildren();
		
		sprite = new ItemSprite();
		add(sprite);
		
		status = new BitmapText( PixelScene.pixelFont);
		add(status);
		
		extra = new BitmapText( PixelScene.pixelFont);
		add(extra);
		
		level = new BitmapText( PixelScene.pixelFont);
		add(level);

		center = new BitmapText( PixelScene.pixelFont);
		add(center);
	}
	
	@Override
	protected void layout() {
		super.layout();
		
		sprite.x = x + margin.left + (width - sprite.width - (margin.left + margin.right)) / 2f;
		sprite.y = y + margin.top + (height - sprite.height - (margin.top + margin.bottom)) / 2f;
		PixelScene.align(sprite);
		
		if (status != null) {
			status.measure();
			if (status.width > width - (margin.left + margin.right)){
				status.scale.set(PixelScene.align(0.8f));
			} else {
				status.scale.set(1f);
			}
			status.x = x + margin.left;
			status.y = y + margin.top;
			PixelScene.align(status);
		}
		
		if (extra != null) {
			extra.x = x + (width - extra.width()) - margin.right;
			extra.y = y + margin.top;
			PixelScene.align(extra);

			if ((status.width() + extra.width()) > width){
				extra.visible = false;
			} else if (item != null) {
				extra.visible = true;
			}
		}

		if (itemIcon != null){
			//center the icon slightly if there is enough room
//			if (width >= 24 || height >= 24) {
//				itemIcon.x = x + width - (物品表.Icons.SIZE + itemIcon.width()) / 2f - margin.right;
//				itemIcon.y = y + (物品表.Icons.SIZE - itemIcon.height) / 2f + margin.top;
//			} else {
				itemIcon.x = x + width - itemIcon.width() - margin.right;
				itemIcon.y = y + margin.top;
//			}
			PixelScene.align(itemIcon);
		}
		
		if (level != null) {
			level.x = x - margin.right;
			level.y = y;
			PixelScene.align(level);
		}
		if (center != null) {
			center.x = x + (width - center.width()) - margin.right;
			center.y = y + (height - center.baseLine() - 1);
			PixelScene.align(center);
		}

	}

	public void alpha( float value ){
		if (!active) value *= 0.3f;
		if (sprite != null)     sprite.alpha(value);
		if (extra != null)      extra.alpha(value);
		if (status != null)     status.alpha(value);
		if (itemIcon != null)   itemIcon.alpha(value);
		if (level != null)      level.alpha(value);
		if (center != null)      center.alpha(value);
	}

	public void clear(){
		item(null);
		enable(true);
		sprite.visible(true);
		sprite.view(物品表.ITEM,null);
		layout();
	}
	
	public void item( Item item ) {
		if (this.item == item) {
			if (item != null) {
				sprite.view( item );
			}
			updateText();
			return;
		}

		this.item = item;

		if (item == null) {

			enable(false);
			sprite.visible(false);

			updateText();
			
		} else {
			
			enable(true);
			sprite.visible(true);

			sprite.view( item );
			updateText();
		}
	}

	public void updateText(){

		if (itemIcon != null){
			remove(itemIcon);
			itemIcon = null;
		}

		if (item == null){
			status.visible = extra.visible = level.visible = center.visible = false;
			return;
		} else {
			status.visible = extra.visible = level.visible = center.visible = true;
		}

		status.text(null);
		extra.text(null);
		center.text(null);
		level.text(null);
		status.text( item.status() );

		if (item.icon != -1 && (item.已鉴定() || (item instanceof Ring && ((Ring) item).isKnown()))){
			extra.text( null );

			itemIcon = new Image(Assets.Sprites.ITEM_ICONS);
			itemIcon.frame(物品表.Icons.film.get(item.icon));
			add(itemIcon);

		} else if (item instanceof Weapon || item instanceof Armor) {

			if (item.levelKnown){
				int str = item instanceof Weapon ? ((Weapon)item).力量() : ((Armor)item).力量();
				extra.text( Messages.format( TXT, str ) );
				if (Dungeon.hero() && str > Dungeon.hero.力量()) {
					if(str == Dungeon.hero.力量()){
						extra.hardlight( WARNING );
					}else{
						extra.hardlight( DEGRADED );
					}
				} else if (item instanceof Weapon && str > Dungeon.hero.力量()&& (((Weapon) item).masteryPotionBonus ||((Weapon) item).神力)){
					extra.hardlight( MASTERED );
				} else if (item instanceof Armor && str > Dungeon.hero.力量()&& (((Armor) item).masteryPotionBonus||((Armor) item).神力)) {
					extra.hardlight( MASTERED );
				} else {
					if(extra.text!=null){
						extra.text(null);
						extra.resetColor();
					}
				}
			} else {
				int str = item instanceof Weapon ? ((Weapon)item).力量(0) : ((Armor)item).力量(0);
			
				extra.text( Messages.format( TXT_TYPICAL_STR, str ) );
				if (Dungeon.hero() && str > Dungeon.hero.力量()) {
					if(str == Dungeon.hero.力量()){
						extra.hardlight( WARNING );
					}else{
						extra.hardlight( DEGRADED );
					}
				} else if (item instanceof Weapon && str > Dungeon.hero.力量()&& (((Weapon) item).masteryPotionBonus ||((Weapon) item).神力)){
					extra.hardlight( MASTERED );
				} else if (item instanceof Armor && str > Dungeon.hero.力量()&& (((Armor) item).masteryPotionBonus||((Armor) item).神力)) {
					extra.hardlight( MASTERED );
				} else {
					if(extra.text!=null){
						extra.text(null);
						extra.resetColor();
					}
				}
			}
			extra.measure();

		} else {
			if(extra.text!=null){
				extra.text(null);
				extra.resetColor();
			}
		}
		
		//装备图标
		if(item.已鉴定()){
			if (item instanceof Food food){
				center.text( Messages.format( TXT, Math.round(food.energy)) );
				center.measure();
				center.hardlight( UPGRADED );
				extra.text( Messages.format( TXT, Math.round(food.energy*0.01f)) );
				extra.measure();
				extra.hardlight( UPGRADED );
			}else if (item instanceof 水袋 s) {
				center.text( Messages.format( TXT, Dungeon.hero.最大生命(0.05f*s.volume)) );
				center.measure();
				center.hardlight( UPGRADED );
			}else if (item instanceof 治疗药剂) {
				center.text( Messages.format( TXT, Dungeon.hero.最大生命(0.9f)) );
				center.measure();
				center.hardlight( UPGRADED );
			}else if (item instanceof PotionOfShielding ) {
				center.text( Messages.format( TXT, Dungeon.hero.最大生命(0.05f)) );
				center.measure();
				center.hardlight( FADED );
			}else if (item instanceof ChaliceOfBlood x&&x.等级()<10) {
				level.text( Messages.format( TXT, 3*x.等级()*x.等级()) );
				level.measure();
				level.hardlight( WARNING );
			}else if (item instanceof 荆棘斗篷 x) {
				extra.text( Messages.format( TXT, x.等级()+5) );
				extra.measure();
				extra.hardlight( FADED );
			}else if (item instanceof 心之钢 x) {
				extra.text( Messages.format( TXT, Dungeon.hero.最大生命(0.1f)+x.等级()+1) );
				extra.measure();
				extra.hardlight( WARNING );
				center.text( Messages.format( TXT, x.心之钢生命));
				center.measure();
				center.hardlight( UPGRADED );
			}else if (item instanceof 武力之戒 x) {
				level.text( Messages.format( TXT, x.soloBuffedBonus()+1) );
				level.measure();
				level.hardlight( WARNING );
			}else{
				if(center.text!=null){
					center.text(null);
					center.resetColor();
				}
			}
		}
		
		int trueLvl = item.visiblyUpgraded();
		int buffedLvl = item.buffedVisiblyUpgraded();
		if(item instanceof 法师魔杖||
		   item instanceof 手枪||
		   item instanceof Ring||
		   item instanceof Weapon||
		   item instanceof Wand||
		   item instanceof Artifact){
			if (trueLvl != 0 || buffedLvl != 0) {
				center.text(Messages.format(TXT_LEVEL, buffedLvl));
				center.measure();
				if (trueLvl == buffedLvl || buffedLvl <= 0) {
					if (buffedLvl > 0) {
						if ((item instanceof Weapon && ((Weapon) item).curseInfusionBonus)
								|| (item instanceof Armor && ((Armor) item).curseInfusionBonus)
								|| (item instanceof Wand && ((Wand) item).curseInfusionBonus)) {
							center.hardlight(CURSE_INFUSED);
						} else {
							center.hardlight(UPGRADED);
						}
					} else {
						center.hardlight(DEGRADED);
					}
				} else {
					center.hardlight(buffedLvl > trueLvl ? ENHANCED : WARNING);
				}
			} else {
				if(center.text!=null){
					center.text(null);
					center.resetColor();
				}
			}
		}else {
			if (trueLvl != 0 || buffedLvl != 0) {
				level.text(Messages.format(TXT_LEVEL, buffedLvl));
				level.measure();
				if (trueLvl == buffedLvl || buffedLvl <= 0) {
					if (buffedLvl > 0) {
						if ((item instanceof Weapon && ((Weapon) item).curseInfusionBonus)
								|| (item instanceof Armor && ((Armor) item).curseInfusionBonus)
								|| (item instanceof Wand && ((Wand) item).curseInfusionBonus)) {
							level.hardlight(CURSE_INFUSED);
						} else {
							level.hardlight(UPGRADED);
						}
					} else {
						level.hardlight(DEGRADED);
					}
				} else {
					level.hardlight(buffedLvl > trueLvl ? ENHANCED : WARNING);
				}
			} else {
				if(level.text!=null){
					level.text(null);
					level.resetColor();
				}
			}
		}
		
		layout();
	}
	
	public void enable( boolean value ) {
		
		active = value;
		//reset properties if was pressed
		if (!active && pressedButton == this){
			hotArea.reset();
			pressedButton = null;
			clickReady = false;
			onPointerUp();
		}
		
		float alpha = value ? ENABLED : DISABLED;
		sprite.alpha( alpha );
		status.alpha( alpha );
		extra.alpha( alpha );
		level.alpha( alpha );
		center.alpha( alpha );
		if (itemIcon != null) itemIcon.alpha( alpha );
	}

	public void showExtraInfo( boolean show ){

		if (show){
			add(extra);
			add(level);
		} else {
			remove(extra);
			remove(level);
		}

	}

	public void textVisible( boolean visible ){
		if (visible){
			add(status);
			add(extra);
			add(level);
			add(center);
		} else {
			remove(status);
			remove(extra);
			remove(level);
			remove(center);
		}
	}

	public void setMargins( int left, int top, int right, int bottom){
		margin.set(left, top, right, bottom);
		layout();
	}

	@Override
	protected String hoverText() {
		if (item != null && item.name() != null) {
			return Messages.titleCase(item.name());
		} else {
			return super.hoverText();
		}
	}
}

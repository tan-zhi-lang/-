

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.骷髅钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.Key;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.磨损钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.Brew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.Elixir;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Spell;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.Trinket;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.飞镖;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MissileSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTextInput;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.shatteredpixel.shatteredpixeldungeon.玩法设置;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.系统设置;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Item implements Bundlable {

	protected static final String TXT_TO_STRING_LVL		= "%s %+d";
	protected static final String TXT_TO_STRING_X		= "%s x%d";
	
	public static final String AC_DROP		= "DROP";
	public static final String AC_THROW		= "THROW";
	public static final String AC_RENAME		= "RENAME";
	public static final String AC_REMOVE		= "REMOVE";

	protected String defaultAction;
	public boolean usesTargeting=true;

	//TODO should these be private and accessed through methods?
	public int image = 0;
	public int icon = -1; //used as an identifier for items with randomized images
	public String name = "";
	
	public boolean 可堆叠= false;
	public boolean 物品 = false;
	public boolean 价值提升 = false;
	public boolean 能量提升 = false;
	public boolean 快速使用 = false;
	public boolean 炼金全放 = false;
	public boolean 升级物品 = true;
	public boolean 可以空间 = true;
	protected int quantity = 1;
	public boolean dropsDownHeap = false;

	public String item_Miss		= Assets.Sounds.MISS;
	public boolean alpha = false;
	protected int 等级 = 0;

	public boolean 白色 = false;
	public boolean 黑色 = false;
	public boolean 黄色 = false;
	public boolean 青色 = false;
	public boolean 紫色 = false;
	public boolean 红色 = false;
	public boolean 绿色 = false;
	public boolean 蓝色 = false;
	public boolean levelKnown = false;

	public boolean cursed=false;
	public boolean cursedKnown=false;
	
	// Unique items persist through revival
	public boolean 特别= false;
	public boolean 缴械= true;
	public boolean 嬗变= true;
	public boolean 专属 = false;
	public boolean 消受投掷 = false;
	public boolean 首次使用= true;
	public boolean 首次拾取 = true;
	public boolean 首次装备 = true;
	public boolean 房间物品 = false;
	public boolean 移除 = false;
	public Item 房间物品(){
		房间物品=true;
		return this;
	}
	public Item 移除(){
		alpha=true;
		移除=true;
		return this;
	}

	// These items are preserved even if the hero's inventory is lost via unblessed ankh
	// this is largely set by the resurrection window, items can override this to always be kept
	public boolean keptThoughLostInvent = false;

	// whether an item can be included in heroes remains
	public boolean 遗产= false;

	public int customNoteID = -1;
	
	public static final Comparator<Item> itemComparator = new Comparator<Item>() {
		@Override
		public int compare( Item lhs, Item rhs ) {
			return Generator.Category.order( lhs ) - Generator.Category.order( rhs );
		}
	};
	
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = new ArrayList<>();
		actions.add( AC_DROP );
		actions.add( AC_THROW );
		
		if(SPDSettings.物品命名())
		actions.add( AC_RENAME );
		
		if(Dungeon.玩法(玩法设置.刷子地牢)&&false)
		actions.add( AC_REMOVE );
		return actions;
	}

	public String actionName(String action, Hero hero){
		return Messages.get(this, "ac_" + action);
	}

	public final boolean doPickUp() {

		return doPickUp( Dungeon.hero, Dungeon.hero.pos );
	}
	public final boolean doPickUp( Hero hero ) {
		return doPickUp( hero, hero.pos );
	}

	public boolean doPickUp(Hero hero, int pos) {
		if(Dungeon.炼狱(炼狱设置.遗失钥匙)){
			if(this instanceof 磨损钥匙||this instanceof 骷髅钥匙){
			}else if(this instanceof Key){
				return false;
			}
		}
		if (放背包( hero.belongings.backpack )) {
			
			GameScene.pickUp( this, pos );
			Sample.INSTANCE.play( Assets.Sounds.ITEM );
			hero.spendAndNext( pickupDelay() );
			return true;
			
		} else {
			return false;
		}
	}
	public float pickupDelay(){
		
		if(Dungeon.hero.heroClass(HeroClass.盗贼)){
			Buff.施加(Dungeon.hero,Swiftthistle.TimeBubble.class).reset(1+(Dungeon.hero.subClass(HeroSubClass.神偷无影)&&Dungeon.hero.职业精通()?2:0));
		}
		if(Dungeon.hero.subClass(HeroSubClass.神偷无影)){
			return 0;
		}
		return Dungeon.hero.攻击延迟();
	}
	public void doDrop( Hero hero ) {
		hero.spendAndNext(Dungeon.hero.攻击延迟());
		int pos = hero.pos;
		Dungeon.level.drop(detachAll(hero.belongings.backpack), pos).sprite().drop(pos);
	}

	//resets an item's properties, to ensure consistency between runs
	public void reset(){
		keptThoughLostInvent = false;
	}

	public boolean keptThroughLostInventory(){
		return keptThoughLostInvent;
	}

	public void doThrow( Hero hero ) {
		if( hero.天赋概率(Talent.消受投掷,25)){
			消受投掷=true;
		}
		GameScene.selectCell(thrower);
	}
	
	public void execute( Hero hero, String action ) {

		GameScene.cancel();
		curUser = hero;
		curItem = this;
		
		if (action.equals( AC_DROP )) {
			
			if (hero.belongings.backpack.contains(this) || isEquipped(hero)) {
				doDrop(hero);
			}
			
		} else if (action.equals( AC_THROW )) {
			if (hero.belongings.backpack.contains(this) || isEquipped(hero)) {
				doThrow(hero);
			}
			
		}else if (action.equals( AC_REMOVE )) {
		} else if (action.equals( AC_RENAME )) {
			GameScene.show(new WndTextInput("物品重命名",
											"",
											"",
											50,
											false,
											"确定",
											"取消"){
				@Override
				public void onSelect(boolean positive, String text) {
					if (positive && !text.isEmpty()){
						name=text;
					}
				}
			});
			
		}
	}

	//can be overridden if default action is variable
	public String defaultAction(){
		return defaultAction;
	}
	
	public void execute( Hero hero ) {
		String action = defaultAction();
		if (action != null) {
			execute(hero, defaultAction());
		}
	}
	
	protected void onThrow( int cell ) {
		if(消受投掷){
			消受投掷=false;
			return;
		}
		Heap heap = Dungeon.level.drop( this, cell );
		if (!heap.isEmpty()) {
			heap.sprite().drop( cell );
		}
	}
	
	//takes two items and merges them (if possible)
	public Item merge( Item other ){
		if (isSimilar( other )){
			quantity += other.quantity;
			other.quantity = 0;
		}
		return this;
	}
	
	public boolean 放背包(Bag container ) {
		if (quantity <= 0){
			return true;
		}
		
		ArrayList<Item> items = container.items;

		if (items.contains( this )) {
			return true;
		}

		for (Item item:items) {
			if (item instanceof Bag && ((Bag)item).canHold( this )) {
				if (放背包( (Bag)item )){
					return true;
				}
			}
		}

		if (!container.canHold(this)){
			return false;
		}
		
		if (可堆叠) {
			for (Item item:items) {
				if (isSimilar( item )) {
					item.merge( this );
					item.updateQuickslot();
					if (Dungeon.hero() && Dungeon.hero.isAlive()) {
						Badges.validateItemLevelAquired( this );
						Talent.拾取时(Dungeon.hero, item);
						if (已鉴定()) {
							Catalog.setSeen(getClass());
							Statistics.itemTypesDiscovered.add(getClass());
						}
					}
					return true;
				}
			}
		}

		if (Dungeon.hero() && Dungeon.hero.isAlive()) {
			Badges.validateItemLevelAquired( this );
			Talent.拾取时( Dungeon.hero, this );
			if (已鉴定()){
				Catalog.setSeen(getClass());
				Statistics.itemTypesDiscovered.add(getClass());
			}
		}
		if(房间物品){
			GLog.w("你能感觉某个房间中的事物需要此物品来解密。");
			房间物品=false;
		}
		if(Dungeon.hero()&&Dungeon.hero.sprite!=null){
			if(!已鉴定()){
				if(可升级()){
					Dungeon.hero.sprite.礼物();
				}else{
					
					Dungeon.hero.sprite.showLost();
				}
			}
		}
		首次拾取=false;
		Dungeon.quickslot.alphaItem( this ,false);
		items.add( this );
		Dungeon.quickslot.replacePlaceholder(this);
		Collections.sort( items, itemComparator );
		updateQuickslot();
		return true;

	}
	
	public final boolean 放背包() {
		boolean 放=放背包( Dungeon.hero.belongings.backpack );
		if(!放){
			Dungeon.level.drop(this,Dungeon.hero.pos);
		}
		return 放;
	}
	
	//returns a new item if the split was sucessful and there are now 2 items, otherwise null
	public Item split( int amount ){
		if (amount <= 0 ||amount>=数量()) {
			return null;
		} else {
			//pssh, who needs copy constructors?
			Item split = Reflection.newInstance(getClass());
			
			if (split == null){
				return null;
			}
			
			Bundle copy = new Bundle();
			this.storeInBundle(copy);
			split.restoreFromBundle(copy);
			split.数量(amount);
			quantity -= amount;
			
			return split;
		}
	}

	public Item duplicate(){
		Item dupe = Reflection.newInstance(getClass());
		if (dupe == null){
			return null;
		}
		Bundle copy = new Bundle();
		this.storeInBundle(copy);
		dupe.restoreFromBundle(copy);
		return dupe;
	}
	
	public final Item detach( Bag container ) {
		
		if (quantity <= 0) {
			return null;
		} else{
			if(Dungeon.系统(系统设置.无限资源)){
				return this;
			}
			if(quantity==1){
				Dungeon.quickslot.alphaItem(Item.this,true);
				updateQuickslot();
				if(可堆叠){
					Dungeon.quickslot.convertToPlaceholder(this);
				}
				
				return detachAll(container);
				
			}else{
				Item detached=split(1);
				updateQuickslot();
				if(detached!=null)
					detached.onDetach();
				return detached;
				
			}
		}
	}
	
	public final Item detachAll( Bag container ) {
		
		if(Dungeon.系统(系统设置.无限资源)){
			return this;
		}
		for (Item item : container.items) {
			if (item == this) {
				container.items.remove(this);
				item.onDetach();
				container.grabItems(); //try to put more items into the bag as it now has free space
				updateQuickslot();
				return this;
			} else if (item instanceof Bag) {
				Bag bag = (Bag)item;
				if (bag.contains( this )) {
					return detachAll( bag );
				}
			}
		}

		updateQuickslot();
		return this;
	}
	
	public boolean isSimilar( Item item ) {
		return getClass() == item.getClass();
	}

	protected void onDetach(){}

	//returns the true level of the item, ignoring all modifiers aside from upgrades
	public final int 真等级(){
		return 等级;
	}

	//returns the persistant level of the item, only affected by modifiers which are persistent (e.g. curse infusion)
	public int 等级(){
		return 等级;
	}
	
	//returns the level of the item, after it may have been modified by temporary boosts/reductions
	//note that not all item properties should care about buffs/debuffs! (e.g. str requirement)
	public int 强化等级(){
		int x=0;
		if(Dungeon.hero()&&Dungeon.hero.heroClass(HeroClass.逐姝)&&this instanceof Weapon){
			x++;
		}
		//only the hero can be affected by Degradation
		if (Dungeon.hero() && Dungeon.hero.buff( Degrade.class ) != null
			&& (isEquipped( Dungeon.hero ) || Dungeon.hero.belongings.contains( this ))) {
			return Degrade.reduceLevel(等级()+x);
		} else {
			return 等级()+x;
		}
	}

	public void 等级(int value ){
		等级 = value;
		updateQuickslot();
	}
	
	public Item 特殊升级() {
		if(!可升级()){
			return this;
		}
		if(升级物品){
			升级();
			升级物品=false;
		}

		updateQuickslot();
		
		return this;
	}
	public Item 升级() {
		
		if(this instanceof Weapon||this instanceof Armor||this instanceof Artifact||this instanceof Ring||this instanceof Wand){
			if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.装备)){
				GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.装备);
			}
			if(Dungeon.玩法(玩法设置.升级概率)){
					if(等级>=17){
						if(算法.概率学(10)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							this.等级(0);
							updateQuickslot();
							return this;
						}
					}
					if(等级>=16){
						if(算法.概率学(11)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							this.等级(0);
							updateQuickslot();
							return this;
						}
					}
					if(等级>=15){
						if(算法.概率学(16)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							this.等级(0);
							updateQuickslot();
							return this;
						}
					}
					if(等级>=14){
						if(算法.概率学(18)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							this.等级(0);
							updateQuickslot();
							return this;
						}
					}
					if(等级>=13){
						if(算法.概率学(20)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							this.等级(0);
							updateQuickslot();
							return this;
						}
					}
					if(等级>=12){
						if(算法.概率学(25)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							this.等级(8);
							updateQuickslot();
							return this;
						}
					}
					if(等级>=11){
						if(算法.概率学(30)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							this.等级(7);
							updateQuickslot();
							return this;
						}
					}
					if(等级>=10){
						if(算法.概率学(35)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							return this;
						}
					}
					if(等级>=9){
						if(算法.概率学(40)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							return this;
						}
					}
					if(等级>=8){
						if(算法.概率学(50)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							return this;
						}
					}
					if(等级>=7){
						if(算法.概率学(60)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							return this;
						}
					}
					if(等级>=6){
						if(算法.概率学(70)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							return this;
						}
					}
					if(等级>=5){
						if(算法.概率学(85)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							return this;
						}
					}
					if(等级>=4){
						if(算法.概率学(95)){
							this.等级++;
							updateQuickslot();
							return this;
						}else{
							return this;
						}
					}
					if(等级<=3){
						this.等级++;
						updateQuickslot();
						return this;
					}
				}
			this.等级++;
			updateQuickslot();
			return this;
		}
		
		this.等级++;
		updateQuickslot();
		return this;
	}
	
	final public Item 升级(int n ) {
		for (int i=0; i < n; i++) {
			升级();
		}
		
		return this;
	}
	
	public Item 降级() {
		
		this.等级--;
		
		return this;
	}
	
	final public Item 降级(int n ) {
		for (int i=0; i < n; i++) {
			降级();
		}
		
		return this;
	}
	
	public int visiblyUpgraded() {
		return levelKnown ? 等级() : 0;
	}

	public int buffedVisiblyUpgraded() {
		return levelKnown ? 强化等级() : 0;
	}
	
	public boolean visiblyCursed() {
		return cursed && cursedKnown;
	}
	
	public boolean 可升级() {
		if(物品){
			return false;
		}
		return true;
	}
	
	public boolean 已鉴定() {
		if(物品){
			return true;
		}
		return levelKnown && cursedKnown;
	}
	
	public boolean isEquipped( Hero hero ) {
		return false;
	}

	public final Item 鉴定(){
		return 鉴定(true);
	}

	public Item 鉴定(boolean byHero ) {
		if(byHero){
			if (Dungeon.hero()&& Dungeon.hero.isAlive()){
				
				Catalog.setSeen(getClass());
				Statistics.itemTypesDiscovered.add(getClass());
			}
		}
		if(Dungeon.解压(解压设置.点石成金)){
			特殊升级();
		}

		levelKnown = true;
		cursedKnown = true;
		Item.updateQuickslot();
		
		return this;
	}
	
	public void onHeroGainExp( float levelPercent, Hero hero ){
		//do nothing by default
	}
	
	public static void evoke( Hero hero ) {
		hero.sprite.emitter().burst( Speck.factory( Speck.EVOKE ), 5 );
	}

	public String title() {

		String name = name();

		if (visiblyUpgraded() != 0)
			name = Messages.format( TXT_TO_STRING_LVL, name, visiblyUpgraded()  );

		if (quantity > 1)
			name = Messages.format( TXT_TO_STRING_X, name, quantity );

		return name;

	}
	
	public String name() {
		return trueName();
	}
	
	public final String trueName() {
		if(!name.equals("")){
			return name;
		}
		return Messages.get(this, "name");
	}
	
	public int image() {
		return image;
	}
	
	public ItemSprite.Glowing glowing() {
		return null;
	}

	public Emitter emitter() { return null; }
	
	public int 价值提升(){
		return (价值提升?Math.round(金币()*1.1f):金币());
	}
	public int 能量提升(){
		return (能量提升?Math.round(能量()*1.5f):能量());
	}
	
	public String info() {

		if (Dungeon.hero()) {
			Notes.CustomRecord note = Notes.findCustomRecord(customNoteID);
			if (note != null) {
				//we swap underscore(0x5F) with low macron(0x2CD) here to avoid highlighting in the item window
				return Messages.get(this, "custom_note", note.title().replace('_', 'ˍ')) + "\n\n" + desc();
			} else {
				note = Notes.findCustomRecord(getClass());
				if (note != null) {
					//we swap underscore(0x5F) with low macron(0x2CD) here to avoid highlighting in the item window
					return Messages.get(this, "custom_note_type", note.title().replace('_', 'ˍ')) + "\n\n" + desc();
				}
			}
		}
		String s="代码名"+(已鉴定()?this.getClass().getSimpleName():"待鉴定");
		s+="\n";
		if(已鉴定()){
			
			s+="_"+"金币价值"+(金币()>0?
									   ""+价值提升():
									   "无价")+"_";
			s+=" ";
			s+="能量价值"+(能量()>0?
								   ""+能量提升():
								   "无价");
			s+="\n";
		}
		s+="物品";
		if(this instanceof Bag){
			s+="、"+"背包";
		}
		if(this instanceof Bomb){
			s+="、"+"炸弹";
		}
		if(this instanceof Food){
			s+="、"+"食物";
		}
		if(this instanceof Key){
			s+="、"+"钥匙";
		}
		if(this instanceof Trinket){
			s+="、"+"禁忌物";
		}
		if(this instanceof Potion){
			s+="、"+"药剂";
		}
		if(this instanceof Elixir){
			s+="、"+"秘药";
		}
		if(this instanceof Brew){
			s+="、"+"魔药";
		}
		if(this instanceof ExoticPotion){
			s+="、"+"合剂";
		}
		if(this instanceof Scroll){
			s+="、"+"卷轴";
		}
		if(this instanceof Spell){
			s+="、"+"结晶";
		}
		if(this instanceof ExoticScroll){
			s+="、"+"秘卷";
		}
		if(this instanceof Runestone){
			s+="、"+"符石";
		}
		if(this instanceof ExoticScroll){
			s+="、"+"秘卷";
		}
		if(this instanceof EquipableItem){
			s+="、"+"装备";
		}
		if(this instanceof Weapon){
			s+="、"+"武器";
		}
		if(this instanceof 飞镖){
			s+="、"+"飞镖";
		}
		if(this instanceof Armor){
			s+="、"+"防具";
		}
		if(this instanceof Artifact){
			s+="、"+"神器";
		}
		if(this instanceof Ring){
			s+="、"+"神器";
		}
		if(this instanceof Wand){
			s+="、"+"法杖";
		}

		return desc()+"\n\n"+s;
	}
	
	public String desc() {
		return Messages.get(this, "desc");
	}
	
	public int 数量() {
		return quantity;
	}
	public int 数量(float x) {
		return Math.round(quantity*x);
	}
	public Item 数量(int value) {
		
		quantity = value;
		return this;
	}

	//item's value in gold coins
	public int 金币() {
		return 0;
	}

	//item's value in energy crystals
	public int 能量() {
		return 0;
	}
	
	public Item virtual(){
		Item item = Reflection.newInstance(getClass());
		if (item == null) return null;
		
		item.quantity = 0;
		item.等级 = 等级;
		return item;
	}
	
	public Item random() {
		return this;
	}
	
	public String status() {
		return quantity != 1 ? Integer.toString( quantity ) : null;
	}

	public static void updateQuickslot() {
		GameScene.updateItemDisplays = true;
	}
	
	private static final String QUANTITY		= "quantity";
	private static final String LEVEL			= "level";
	private static final String LEVEL_KNOWN		= "levelKnown";
	private static final String CURSED			= "cursed";
	private static final String CURSED_KNOWN	= "cursedKnown";
	private static final String QUICKSLOT		= "quickslotpos";
	private static final String KEPT_LOST       = "kept_lost";
	private static final String CUSTOM_NOTE_ID = "custom_note_id";
	private static final String NAME = "name";
	private static final String 升级物品x = "升级物品";
	private static final String 首次使用x = "首次使用";
	private static final String 首次拾取x = "首次拾取";
	private static final String 首次装备x = "首次装备";
	private static final String 房间物品x = "房间物品";
	private static final String 价值提升x = "价值提升";
	private static final String 能量提升x = "能量提升";
	private static final String 快速使用x = "快速使用";
	private static final String 移除x = "移除";
	private static final String ALPHA = "alpha";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		bundle.put( QUANTITY, quantity );
		bundle.put( LEVEL, 等级);
		bundle.put( LEVEL_KNOWN, levelKnown );
		bundle.put( CURSED, cursed );
		bundle.put( CURSED_KNOWN, cursedKnown );
		bundle.put( NAME, name );
		bundle.put( 升级物品x, 升级物品 );
		bundle.put(首次使用x,首次使用);
		bundle.put( 首次拾取x, 首次拾取 );
		bundle.put( 首次装备x, 首次装备 );
		bundle.put( 房间物品x, 房间物品 );
		bundle.put( 价值提升x, 价值提升 );
		bundle.put( 能量提升x, 能量提升 );
		bundle.put( 快速使用x, 快速使用 );
		bundle.put( 移除x, 移除 );
		bundle.put( ALPHA, alpha );
		if (Dungeon.quickslot.contains(this)) {
			bundle.put( QUICKSLOT, Dungeon.quickslot.getSlot(this) );
		}
		bundle.put( KEPT_LOST, keptThoughLostInvent );
		if (customNoteID != -1)     bundle.put(CUSTOM_NOTE_ID, customNoteID);
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		quantity	= bundle.getInt( QUANTITY );
		levelKnown	= bundle.getBoolean( LEVEL_KNOWN );
		cursedKnown	= bundle.getBoolean( CURSED_KNOWN );
		name	= bundle.getString( NAME );
		升级物品	= bundle.getBoolean( 升级物品x );
		首次使用= bundle.getBoolean(首次使用x);
		首次拾取	= bundle.getBoolean( 首次拾取x );
		首次装备	= bundle.getBoolean( 首次装备x );
		房间物品	= bundle.getBoolean( 房间物品x );
		价值提升	= bundle.getBoolean( 价值提升x );
		能量提升	= bundle.getBoolean( 能量提升x );
		快速使用	= bundle.getBoolean( 快速使用x );
		移除	= bundle.getBoolean( 移除x );
		alpha	= bundle.getBoolean( ALPHA );
		
		int level = bundle.getInt( LEVEL );
		if (level > 0) {
			升级( level );
		} else if (level < 0) {
			降级( -level );
		}
		
		cursed	= bundle.getBoolean( CURSED );
		//only want to populate slots when restoring belongings
		if (Belongings.bundleRestoring) {
			if (bundle.contains(QUICKSLOT)) {
				Dungeon.quickslot.setSlot(bundle.getInt(QUICKSLOT), this);
			}
		}

		keptThoughLostInvent = bundle.getBoolean( KEPT_LOST );
		if (bundle.contains(CUSTOM_NOTE_ID))    customNoteID = bundle.getInt(CUSTOM_NOTE_ID);
	}

	public int targetingPos( Hero user, int dst ){
		return throwPos( user, dst );
	}

	public int throwPos( Hero user, int dst){
		return new Ballistica( user.pos, dst, Ballistica.PROJECTILE ).collisionPos;
	}

	public void throwSound(){
		Sample.INSTANCE.play(item_Miss, 0.6f, 0.6f, 1.5f);
	}
	
	public void cast( final Hero user, final int dst ) {
		
		final int cell = throwPos( user, dst );
		user.sprite.zap( cell );
		user.busy();

		throwSound();

		Char enemy = Actor.findChar( cell );
		QuickSlotButton.target(enemy);
		
		final float delay = castDelay(user, cell);

		if (enemy != null) {
			((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
					reset(user.sprite,
							enemy.sprite,
							this,
							new Callback() {
						@Override
						public void call() {
							curUser = user;
							Item i;
							if(消受投掷){
								i=Item.this;
								Dungeon.quickslot.alphaItem(Item.this,false);
								updateQuickslot();
							}else{
								i=Item.this.detach(user.belongings.backpack);
							}
							if (i != null) i.onThrow(cell);
							//给效果
							if (user.buff(Talent.LethalMomentumTracker.class) != null){
								user.buff(Talent.LethalMomentumTracker.class).detach();
								user.next();
							} else {
								if(快速使用){
									user.spendAndNext(0);
								}else{
									user.spendAndNext(delay);
								}
							}
						}
					});
		} else {
			((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
					reset(user.sprite,
							cell,
							this,
							new Callback() {
						@Override
						public void call() {
							curUser = user;
							Item i;
							if(消受投掷){
								i=Item.this;
								Dungeon.quickslot.alphaItem(Item.this,false);
								updateQuickslot();
								
							}else{
								i=Item.this.detach(user.belongings.backpack);
							}
							
							if(快速使用){
								user.spend(0);
							}else{
								user.spend(delay);
							}
							
							if (i != null) i.onThrow(cell);
							user.next();
						}
					});
		}
		
		usesTargeting=false;
	}
	
	public float castDelay( Char user, int cell ){
		return Dungeon.hero.攻击延迟();
	}
	
	public static Hero curUser = null;
	public static Item curItem = null;
	public void setCurrent( Hero hero ){
		curUser = hero;
		curItem = this;
	}

	protected static CellSelector.Listener thrower = new CellSelector.Listener() {
		@Override
		public void onSelect( Integer target ) {
			if (target != null) {
				curItem.cast( curUser, target );
			}
		}
		@Override
		public String prompt() {
			return Messages.get(Item.class, "prompt");
		}
	};
}

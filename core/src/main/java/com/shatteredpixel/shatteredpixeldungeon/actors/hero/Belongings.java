

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindofMisc;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.技能;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Belongings implements Iterable<Item> {

	private Hero owner;

	public static class Backpack extends Bag {
		{
			image = 物品表.BACKPACK;
		}
		public int capacity(){
			int cap = super.capacity();
			for (Item item : items){
				if (item instanceof Bag){
					cap++;
				}
			}
			//secondary weapons still occupy an inv. slot
			if(Dungeon.hero()){
//				if(Dungeon.hero.belongings.armor!=null) cap--;
//				if(Dungeon.hero.belongings.weapon!=null) cap--;
//				if(Dungeon.hero.belongings.幸运!=null) cap--;
//				if(Dungeon.hero.belongings.armor2!=null) cap--;
//				if(Dungeon.hero.belongings.secondWep!=null) cap--;

			}
			return cap;
		}
	}

	public Backpack backpack;
	/**

	 if(算法.概率学(33)){
	 LinkedList<Item> items = new LinkedList<>();
	 for(Item item:belongings){
	 items.add(item);
	 }
	 GLog.p("b"+items);
	 }
	 else if(算法.概率学(50)){
	 LinkedList<Item> items = new LinkedList<>();
	 for(Item item:belongings.backpack){
	 items.add(item);
	 }
	 GLog.p("bb"+items);
	 }
	 else{
	 LinkedList<Item> items = new LinkedList<>();
	 for(Item item:belongings.backpack.items){
	 items.add(item);
	 }
	 GLog.p("bbi"+items);
	 }

	 belonings所有物品包括装备
	 belonings.backpack背包物品
	 belonings.backpack.items主背包物品
	*/
	public Belongings( Hero owner ) {
		this.owner = owner;

		backpack = new Backpack();
		backpack.owner = owner;

	}

	public Weapon weapon = null;
	//used by the champion subclass
	public Weapon secondWep = null;
	public Armor armor = null;
	public Armor armor2 = null;
	public KindofMisc misc = null;
	public KindofMisc misc2 = null;
	public KindofMisc misc3 = null;
	public KindofMisc misc4 = null;
	public KindofMisc misc5 = null;
	public KindofMisc misc6 = null;
	public KindofMisc misc7 = null;
	public KindofMisc misc8 = null;
	public KindofMisc misc9 = null;
	public KindofMisc misc10 = null;
	public KindofMisc 幸运 = null;
	public LinkedList<Item> 装备(){
		LinkedList<Item> items = new LinkedList<>();
		if(weapon()!=null)
		items.add(weapon());
		if(armor()!=null)
		items.add(armor());


		if(misc()!=null)
		items.add(misc());
		if(misc2()!=null)
		items.add(misc2());
		if(misc3()!=null)
		items.add(misc3());
		if(misc4()!=null)
		items.add(misc4());
		if(misc5()!=null)
		items.add(misc5());
		if(misc6()!=null)
		items.add(misc6());
		if(misc7()!=null)
		items.add(misc7());
		if(misc8()!=null)
		items.add(misc8());
		if(misc9()!=null)
		items.add(misc9());
		if(misc10()!=null)
		items.add(misc10());

		if(幸运()!=null)
		items.add(幸运());
		return items;
	}

	//used when thrown weapons temporary become the current weapon
	public Weapon thrownWeapon = null;

	//used to ensure that the duelist always uses the weapon she's using the ability of
	public Weapon abilityWeapon = null;


	//*** these accessor methods are so that worn items can be affected by various effects/debuffs
	// we still want to access the raw equipped items in cases where effects should be ignored though,
	// such as when equipping something, showing an interface, or dealing with items from a dead hero

	//normally the primary equipped weapon, but can also be a thrown weapon or an ability's weapon
	public Weapon 投掷武器(){
		if (thrownWeapon != null) return thrownWeapon;
		return null;
	}public Weapon attackingWeapon(){
		if (thrownWeapon != null) return thrownWeapon;
		return weapon();
	}

	//we cache whether belongings are lost to avoid lots of calls to hero.buff(LostInventory.class)
	private boolean lostInvent;
	public void lostInventory( boolean val ){
		lostInvent = val;
	}

	public boolean lostInventory(){
		return lostInvent;
	}

	public Weapon weapon(){
			//if the hero has two weapons (champion), pick the stronger one
			Weapon item =null;

			if(weapon1()!=null)item=weapon1();

			if(weapon2()!=null)item=weapon2();

			if(weapon1()!=null&&weapon2()!=null){
				if(weapon2().DPS()>weapon1().DPS()){
					item=weapon2();
				}
			}

		return item;

	}

	public boolean hasweapon1(){
		return weapon1()!=null;
	}
	public Weapon weapon1(){
		if (!lostInventory() || (weapon != null && weapon.keptThroughLostInventory())){
			return weapon;
		} else {
			return null;
		}
	}
	public boolean hasweapon2(){
		return weapon2()!=null;
	}
	public Weapon weapon2(){
		if (!lostInventory() || (secondWep != null && secondWep.keptThroughLostInventory())){
			return secondWep;
		} else {
			return null;
		}
	}

	public Armor armor(){
		if (!lostInventory() ){
			//if the hero has two weapons (champion), pick the stronger one
			Armor item =null;
			if((armor != null && armor.keptThroughLostInventory()))
				item=armor;

			if((armor2 != null && armor2.keptThroughLostInventory()))
				item=armor2;

			if(armor!=null&&armor2!=null)
				if (armor2.DR()>armor.DR()){
					item = armor2;
				}

			if(item!=null)
				return item;
		}

		return null;
	}

	public boolean hasarmor1(){
		return armor1()!=null;
	}
	public Armor armor1(){
		if (!lostInventory() || (armor != null && armor.keptThroughLostInventory())){
			return armor;
		} else {
			return null;
		}
	}
	public boolean hasarmor2(){
		return armor2()!=null;
	}
	public Armor armor2(){
		if (!lostInventory() || (armor2 != null && armor2.keptThroughLostInventory())){
			return armor2;
		} else {
			return null;
		}
	}
	public KindofMisc misc(){
		if (!lostInventory() || (misc != null && misc.keptThroughLostInventory())){
			return misc;
		} else {
			return null;
		}
	}
	public KindofMisc misc2(){
		if (!lostInventory() || (misc2 != null && misc2.keptThroughLostInventory())){
			return misc2;
		} else {
			return null;
		}
	}
	public KindofMisc misc3(){
		if (!lostInventory() || (misc3 != null && misc3.keptThroughLostInventory())){
			return misc3;
		} else {
			return null;
		}
	}

	public KindofMisc misc4(){
		if (!lostInventory() || (misc4 != null && misc4.keptThroughLostInventory())){
			return misc4;
		} else {
			return null;
		}
	}

	public KindofMisc misc5(){
		if (!lostInventory() || (misc5 != null && misc5.keptThroughLostInventory())){
			return misc5;
		} else {
			return null;
		}
	}
	public KindofMisc misc6(){
		if (!lostInventory() || (misc6 != null && misc6.keptThroughLostInventory())){
			return misc6;
		} else {
			return null;
		}
	}
	public KindofMisc misc7(){
		if (!lostInventory() || (misc7 != null && misc7.keptThroughLostInventory())){
			return misc7;
		} else {
			return null;
		}
	}
	public KindofMisc misc8(){
		if (!lostInventory() || (misc8 != null && misc8.keptThroughLostInventory())){
			return misc8;
		} else {
			return null;
		}
	}

	public KindofMisc misc9(){
		if (!lostInventory() || (misc9 != null && misc9.keptThroughLostInventory())){
			return misc9;
		} else {
			return null;
		}
	}
	public KindofMisc misc10(){
		if (!lostInventory() || (misc10 != null && misc10.keptThroughLostInventory())){
			return misc10;
		} else {
			return null;
		}
	}

	public KindofMisc 幸运(){
		if (!lostInventory() || (幸运 != null && 幸运.keptThroughLostInventory())){
			return 幸运;
		} else {
			return null;
		}
	}

	// ***

	private static final String WEAPON		= "weapon";
	private static final String ARMOR		= "armor";
	private static final String ARMOR2		= "armor2";
	private static final String MISC       = "misc";
	private static final String MISC2       = "misc2";
	private static final String MISC3       = "misc3";
	private static final String MISC4       = "misc4";
	private static final String MISC5       = "misc5";
	private static final String MISC6       = "misc6";
	private static final String MISC7       = "misc7";
	private static final String MISC8       = "misc8";
	private static final String MISC9       = "misc9";
	private static final String MISC10      = "misc10";
	private static final String 幸运x       = "幸运";

	private static final String SECOND_WEP = "second_wep";

	public void storeInBundle( Bundle bundle ) {

		backpack.storeInBundle( bundle );

		bundle.put( WEAPON, weapon );
		bundle.put( ARMOR, armor );
		bundle.put( ARMOR2, armor2 );
		bundle.put( MISC, misc );
		bundle.put( MISC2, misc2 );
		bundle.put( MISC3, misc3 );
		bundle.put( MISC4, misc4 );
		bundle.put( MISC5, misc5 );
		bundle.put( MISC6, misc6 );
		bundle.put( MISC7, misc7 );
		bundle.put( MISC8, misc8 );
		bundle.put( MISC9, misc9 );
		bundle.put( MISC10, misc10 );
		bundle.put( 幸运x, 幸运 );
		bundle.put( SECOND_WEP, secondWep );
	}

	public static boolean bundleRestoring = false;
	public void restoreFromBundle( Bundle bundle ) {

		bundleRestoring = true;
		backpack.clear();
		backpack.restoreFromBundle( bundle );


		weapon = (Weapon) bundle.get(WEAPON);
		if (weapon() != null)       weapon().activate(owner);

		armor = (Armor)bundle.get( ARMOR );
		if (armor() != null)        armor().activate( owner );

		armor2 = (Armor)bundle.get( ARMOR2 );
		if (armor2() != null)        armor2().activate( owner );

		misc = (KindofMisc) bundle.get(MISC);
		if (misc() != null)         misc().activate( owner );


		misc2 = (KindofMisc) bundle.get(MISC2);
		if (misc2() != null)         misc2().activate( owner );


		misc3 = (KindofMisc) bundle.get(MISC3);
		if (misc3() != null)         misc3().activate( owner );

		misc4 = (KindofMisc) bundle.get(MISC4);
		if (misc4() != null)         misc4().activate( owner );

		misc5 = (KindofMisc) bundle.get(MISC5);
		if (misc5() != null)         misc5().activate( owner );

		misc6 = (KindofMisc) bundle.get(MISC6);
		if (misc6() != null)         misc6().activate( owner );

		misc7 = (KindofMisc) bundle.get(MISC7);
		if (misc7() != null)         misc7().activate( owner );

		misc8 = (KindofMisc) bundle.get(MISC8);
		if (misc8() != null)         misc8().activate( owner );

		misc9 = (KindofMisc) bundle.get(MISC9);
		if (misc9() != null)         misc9().activate( owner );

		misc10 = (KindofMisc) bundle.get(MISC10);
		if (misc10() != null)        misc10().activate( owner );
		幸运 = (KindofMisc) bundle.get(幸运x);
		if (幸运() != null)         幸运().activate( owner );

		secondWep = (Weapon) bundle.get(SECOND_WEP);
		if (weapon2()!=null)    weapon2().activate(owner);
		bundleRestoring = false;
	}

	public void clear(){
		backpack.clear();
		weapon = secondWep = null;
		armor = armor2 = null;
		misc = misc2 = misc3 = misc4 = misc5 = misc6 = misc7 = misc8 = misc9 = misc10 = null;
		幸运 = null;
	}

	public static void preview( GamesInProgress.Info info, Bundle bundle ) {
		if (bundle.contains( ARMOR )){
			Armor armor = ((Armor)bundle.get( ARMOR ));
			if (armor instanceof ClassArmor||armor.专属){
				info.armorTier = 6;
				if(Dungeon.hero.heroClass(HeroClass.灵猫)||
				   Dungeon.hero.heroClass(HeroClass.鼠弟)){
					info.armorTier = 1;
				}
			} else {
				info.armorTier = armor.tier();
			}
		} else {
			info.armorTier = 0;
		}
	}

	//ignores lost inventory debuff
	public ArrayList<Bag> getBags(){
		ArrayList<Bag> result = new ArrayList<>();

		result.add(backpack);

		for (Item i : this){
			if (i instanceof Bag){
				result.add((Bag)i);
			}
		}

		return result;
	}
	public boolean weapon盾(){
		boolean 是=false;
		if(weapon!=null&&weapon.盾())
			是=true;
		if(secondWep!=null&&secondWep.盾())
			是=true;
		return 是;
	}
	public boolean weapon剑(){
		boolean 是=false;
		if(weapon!=null&&weapon.剑())
			是=true;
		if(secondWep!=null&&secondWep.剑())
			是=true;
		return 是;
	}
	public boolean weapon锤(){
		boolean 是=false;
		if(weapon!=null&&weapon.锤())
			是=true;
		if(secondWep!=null&&secondWep.锤())
			是=true;
		return 是;
	}
	public boolean weapon斧(){
		boolean 是=false;
		if(weapon!=null&&weapon.斧())
			是=true;
		if(secondWep!=null&&secondWep.斧())
			是=true;
		return 是;
	}
	public boolean weapon棍(){
		boolean 是=false;
		if(weapon!=null&&weapon.棍())
			是=true;
		if(secondWep!=null&&secondWep.棍())
			是=true;
		return 是;
	}
	public boolean weapon鞭(){
		boolean 是=false;
		if(weapon!=null&&weapon.鞭())
			是=true;
		if(secondWep!=null&&secondWep.鞭())
			是=true;
		return 是;
	}
	public boolean weapon刀(){
		boolean 是=false;
		if(weapon!=null&&weapon.刀())
			是=true;
		if(secondWep!=null&&secondWep.刀())
			是=true;
		return 是;
	}
	public boolean weapon(Class itemClass){
		return itemClass.isInstance( weapon )||itemClass.isInstance( secondWep );
	}
	public boolean armor(Class itemClass){
		return itemClass.isInstance( armor )||itemClass.isInstance( armor2 );
	}
	public boolean hasItem(Class itemClass){
		return getItem(itemClass)!=null;
	}
	public boolean 技能(Class itemClass){

		if(hasItem(itemClass)){
			return true;
		}
		return false;
	}
	public boolean 充满技能(Class itemClass){

		if(hasItem(itemClass)&&getItem(itemClass) instanceof 技能 k){
			k.gainCharge(1);
			if(k.满充能()){
				k.wandUsed();
				return true;
			}
		}
		return false;
	}
	public int 技能等级(Class itemClass){

		if(hasItem(itemClass)){
			return getItem(itemClass).强化等级();
		}
		return 0;
	}
	@SuppressWarnings("unchecked")
	public<T extends Item> T getItem( Class<T> itemClass ) {

		boolean lostInvent = lostInventory();

		for (Item item : this) {
			if (itemClass.isInstance( item )) {
				if (!lostInvent || item.keptThroughLostInventory()) {
					return (T) item;
				}
			}
		}
		
		return null;
	}


	public<T extends Item> ArrayList<T> getAllItems( Class<T> itemClass ) {
		ArrayList<T> result = new ArrayList<>();

		boolean lostInvent = lostInventory();

		for (Item item : this) {
			if (itemClass.isInstance( item )) {
				if (!lostInvent || item.keptThroughLostInventory()) {
					result.add((T) item);
				}
			}
		}

		return result;
	}
	
	public boolean contains( Item contains ){

		boolean lostInvent = lostInventory();
		
		for (Item item : this) {
			if (contains == item) {
				if (!lostInvent || item.keptThroughLostInventory()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public Item getSimilar( Item similar ){

		boolean lostInvent = lostInventory();
		
		for (Item item : this) {
			if (similar != item && similar.isSimilar(item)) {
				if (!lostInvent || item.keptThroughLostInventory()) {
					return item;
				}
			}
		}
		
		return null;
	}
	
	public ArrayList<Item> getAllSimilar( Item similar ){
		ArrayList<Item> result = new ArrayList<>();

		boolean lostInvent = lostInventory();
		
		for (Item item : this) {
			if (item != similar && similar.isSimilar(item)) {
				if (!lostInvent || item.keptThroughLostInventory()) {
					result.add(item);
				}
			}
		}
		
		return result;
	}

	//triggers when a run ends, so ignores lost inventory effects
	public void identify() {
		for (Item item : this) {
			item.鉴定(false);
		}
	}
	
	public void observe() {
		if (weapon() != null) {
			if (ShardOfOblivion.passiveIDDisabled() && weapon() instanceof Weapon){
				((Weapon) weapon()).setIDReady();
			} else {
				weapon().鉴定();
				Badges.validateItemLevelAquired(weapon());
			}
		}
		if (weapon2()!=null){
			if (ShardOfOblivion.passiveIDDisabled()&&weapon2() instanceof Weapon){
				((Weapon) weapon2()).setIDReady();
			} else {
				weapon2().鉴定();
				Badges.validateItemLevelAquired(weapon2());
			}
		}
		if (armor() != null) {
			if (ShardOfOblivion.passiveIDDisabled()){
				armor().setIDReady();
			} else {
				armor().鉴定();
				Badges.validateItemLevelAquired(armor());
			}
		}
		if (armor2() != null) {
			if (ShardOfOblivion.passiveIDDisabled()){
				armor2().setIDReady();
			} else {
				armor2().鉴定();
				Badges.validateItemLevelAquired(armor2());
			}
		}
		if (misc() != null) {
			if (ShardOfOblivion.passiveIDDisabled() && misc() instanceof Ring){
				((Ring) misc()).setIDReady();
			} else {
				misc().鉴定();
				Badges.validateItemLevelAquired(misc());
			}
		}
		if (misc2() != null) {
			if (ShardOfOblivion.passiveIDDisabled() && misc2() instanceof Ring){
				((Ring) misc2()).setIDReady();
			} else {
				misc2().鉴定();
				Badges.validateItemLevelAquired(misc2());
			}
		}
		if (misc3() != null) {
			if (ShardOfOblivion.passiveIDDisabled() && misc3() instanceof Ring){
				((Ring) misc3()).setIDReady();
			} else {
				misc3().鉴定();
				Badges.validateItemLevelAquired(misc3());
			}
		}
		if (misc4() != null) {
			if (ShardOfOblivion.passiveIDDisabled() && misc4() instanceof Ring){
				((Ring) misc4()).setIDReady();
			} else {
				misc4().鉴定();
				Badges.validateItemLevelAquired(misc4());
			}
		}
		if (misc5() != null) {
			if (ShardOfOblivion.passiveIDDisabled() && misc5() instanceof Ring){
				((Ring) misc5()).setIDReady();
			} else {
				misc5().鉴定();
				Badges.validateItemLevelAquired(misc5());
			}
		}
		if (幸运() != null) {
			if (ShardOfOblivion.passiveIDDisabled() && 幸运() instanceof Ring){
				((Ring) 幸运()).setIDReady();
			} else {
				幸运().鉴定();
				Badges.validateItemLevelAquired(幸运());
			}
		}
		if (ShardOfOblivion.passiveIDDisabled()){
			GLog.绿(Messages.get(ShardOfOblivion.class,"identify_ready_worn"));
		}
		for (Item item : backpack) {
			if (item instanceof EquipableItem || item instanceof Wand) {
				item.cursedKnown = true;
			}
		}
		Item.updateQuickslot();
	}
	
	public void uncurseEquipped() {
		祛邪卷轴.祛邪(owner,armor(),armor2(),
					  weapon(),misc(),misc2(),misc3(),misc4(),misc5(),
					  幸运(),weapon2());
	}
	
	public Item randomUnequipped() {
		if (owner.buff(LostInventory.class) != null) return null;

		return Random.element( backpack.items );
	}
	
	public int charge( float charge ) {
		
		int count = 0;
		
		for (Wand.Charger charger : owner.buffs(Wand.Charger.class)){
			charger.gainCharge(charge);
			count++;
		}
		
		for (Weapon.Charger charger : owner.buffs(Weapon.Charger.class)){
			charger.gainCharge(charge);
			count++;
		}
		
		return count;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ItemIterator();
	}
	
	private class ItemIterator implements Iterator<Item> {

		private int index = 0;
		
		private Iterator<Item> backpackIterator = backpack.iterator();
		
		private Item[] equipped = {weapon, armor, misc, misc2, misc3, 幸运,
				secondWep, armor2, misc4, misc5
				,misc6,misc7,misc8,misc9,misc10
		};
		private int backpackIndex = equipped.length;
		
		@Override
		public boolean hasNext() {
			
			for (int i=index; i < backpackIndex; i++) {
				if (equipped[i] != null) {
					return true;
				}
			}
			
			return backpackIterator.hasNext();
		}

		@Override
		public Item next() {
			
			while (index < backpackIndex) {
				Item item = equipped[index++];
				if (item != null) {
					return item;
				}
			}
			
			return backpackIterator.next();
		}

		@Override
		public void remove() {
			switch (index) {
			case 0:
				equipped[0] = weapon = null;
				break;
			case 1:
				equipped[1] = armor = null;
				break;
			case 2:
				equipped[2] = misc = null;
				break;
			case 3:
				equipped[3] = misc2 = null;
				break;
			case 4:
				equipped[4] = misc3 = null;
				break;
			case 5:
				equipped[5] = 幸运 = null;
				break;
			case 6:
				equipped[6] = secondWep = null;
				break;
			case 7:
				equipped[7] = armor2 = null;
				break;
			case 8:
					equipped[8] = misc4 = null;
					break;
			case 9:
					equipped[9] = misc5 = null;
					break;
			case 10:
					equipped[10] = misc6 = null;
					break;
			case 11:
				equipped[11] = misc7 = null;
				break;
			case 12:
				equipped[12] = misc8 = null;
				break;
			case 13:
				equipped[13] = misc9 = null;
				break;
			case 14:
				equipped[14] = misc10 = null;
				break;
			default:
				backpackIterator.remove();
			}
		}
	}
}

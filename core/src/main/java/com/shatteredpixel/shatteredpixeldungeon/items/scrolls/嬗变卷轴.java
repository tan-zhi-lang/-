

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.Transmuting;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.勇装;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.巫服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.忍服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.战甲;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.披风;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.武服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.法袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.祭服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.背心;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.胸铠;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.能袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.训服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.连裙;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.道袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.铠甲;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.风衣;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.魔披;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.Brew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.Elixir;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Pickaxe;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.Trinket;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.冰门重盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.Dart;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.TippedDart;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.levels.MiningLevel;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Reflection;

public class 嬗变卷轴 extends InventoryScroll {
	
	{
		icon = 物品表.Icons.SCROLL_TRANSMUTE;
		
		bones = true;

		talentFactor = 2f;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		
		if(item instanceof 冰门重盾){
			return false;
		}
		if(item instanceof 铠甲||
		   item instanceof 法袍||
		   item instanceof 风衣||
		   item instanceof 披风||
		   item instanceof 胸铠||
		   item instanceof 祭服||
		   item instanceof 巫服||
		   item instanceof 武服||
		   item instanceof 道袍||
		   item instanceof 战甲||
		   item instanceof 忍服||
		   item instanceof 能袍||
		   item instanceof 勇装||
		   item instanceof 连裙||
		   item instanceof 训服||
		   item instanceof 背心||
		   item instanceof 魔披
		){
			return false;
		}
		 if (item instanceof MeleeWeapon){
			//all melee weapons, except pickaxe when in a mining level
			return !(item instanceof Pickaxe && Dungeon.level instanceof MiningLevel);

		//all missile weapons except untipped darts
		} else if (item instanceof MissileWeapon){
			return item.getClass() != Dart.class;

		//all regular or exotic potions. No brews or elixirs
		} else if (item instanceof Potion){
			return !(item instanceof Elixir || item instanceof Brew);

		//all regular or exotic scrolls, except itself (unless un-ided, in which case it was already consumed)
		} else if (item instanceof Scroll) {
			return item != this||item.数量()>1||identifiedByUse;

		//all non-unique artifacts (no holy tome or cloak of shadows, basically)
		} else if (item instanceof Artifact) {
			return !item.unique;

		//all rings, wands, trinkets, seeds, and runestones
		} else {
			return item instanceof Ring||item instanceof Armor||item instanceof Wand||item instanceof Trinket
				   ||item instanceof Plant.Seed||item instanceof Runestone;
		}
	}
	
	@Override
	protected void onItemSelected(Item item) {
		
		Item result = changeItem(item);
		
		if (result == null){
			//This shouldn't ever trigger
			GLog.n( Messages.get(this, "nothing") );
			curItem.放背包( curUser.belongings.backpack );
		} else {
			if (result != item) {
				int slot = Dungeon.quickslot.getSlot(item);
				if (item.isEquipped(Dungeon.hero)) {
					item.cursed = false; //to allow it to be unequipped
					if (item instanceof Artifact && result instanceof Ring){
						//if we turned an equipped artifact into a ring, ring goes into inventory
						((EquipableItem) item).doUnequip(Dungeon.hero, false);
						result.放背包();
					} else if (item instanceof KindOfWeapon && Dungeon.hero.belongings.secondWep() == item){
						((EquipableItem) item).doUnequip(Dungeon.hero, false);
						((KindOfWeapon) result).equipSecondary(Dungeon.hero);
					} else {
						((EquipableItem) item).doUnequip(Dungeon.hero, false);
						((EquipableItem) result).doEquip(Dungeon.hero);
					}
					Dungeon.hero.spend(-Dungeon.hero.cooldown()); //cancel equip/unequip time
				} else {
					if (item instanceof MissileWeapon){
						item.detachAll(Dungeon.hero.belongings.backpack);
					} else {
						item.detach(Dungeon.hero.belongings.backpack);
					}
					if (!result.放背包()) {
						Dungeon.level.drop(result, curUser.pos).sprite.drop();
					} else if (result.stackable && Dungeon.hero.belongings.getSimilar(result) != null){
						result = Dungeon.hero.belongings.getSimilar(result);
					}
				}
				if (slot != -1
						&& result.defaultAction() != null
						&& !Dungeon.quickslot.isNonePlaceholder(slot)
						&& Dungeon.hero.belongings.contains(result)){
					Dungeon.quickslot.setSlot(slot, result);
				}
			}
			if (result.已鉴定()){
				Catalog.setSeen(result.getClass());
				Statistics.itemTypesDiscovered.add(result.getClass());
			}
			Transmuting.show(curUser, item, result);
			curUser.sprite.emitter().start(Speck.factory(Speck.CHANGE), 0.2f, 10);
			GLog.p( Messages.get(this, "morph") );
		}
		
	}

	public static Item changeItem( Item item ){
		if (item instanceof 法师魔杖) {
			return changeStaff((法师魔杖) item);
		}else if (item instanceof TippedDart){
			return changeTippedDart( (TippedDart)item );
		} else if (item instanceof MeleeWeapon || item instanceof MissileWeapon) {
			return changeWeapon( (Weapon)item );
		} else if (item instanceof Scroll) {
			return changeScroll( (Scroll)item );
		} else if (item instanceof Potion) {
			return changePotion( (Potion)item );
		} else if (item instanceof Ring) {
			return changeRing( (Ring)item );
		} else if (item instanceof Wand) {
			return changeWand( (Wand)item );
		} else if (item instanceof Plant.Seed) {
			return changeSeed((Plant.Seed) item);
		} else if (item instanceof Runestone) {
			return changeStone((Runestone) item);
		} else if (item instanceof Artifact) {
			Artifact a = changeArtifact( (Artifact)item );
			if (a == null){
				//if no artifacts are left, generate a random ring with shared ID/curse state
				//artifact and ring levels are not exactly equivalent, give the ring up to +2
				Item result = Generator.randomUsingDefaults(Generator.Category.RING);
				result.levelKnown = item.levelKnown;
				result.cursed = item.cursed;
				result.cursedKnown = item.cursedKnown;
				if (item.visiblyUpgraded() == 10){
					result.等级(2);
				} else if (item.visiblyUpgraded() >= 5){
					result.等级(1);
				} else {
					result.等级(0);
				}
				return result;
			} else {
				return a;
			}
		} else if (item instanceof Trinket) {
			return changeTrinket( (Trinket)item );
		} else {
			return null;
		}
	}
	
	private static 法师魔杖 changeStaff(法师魔杖 staff ){
		Class<?extends Wand> wandClass = staff.wandClass();
		
		if (wandClass == null){
			return null;
		} else {
			Wand n;
			do {
				n = (Wand) Generator.randomUsingDefaults(Generator.Category.WAND);
			} while (Challenges.isItemBlocked(n) || n.getClass() == wandClass);
			n.cursed = false;
			n.等级(0);
			n.鉴定();
			staff.imbueWand(n, null);
		}
		
		return staff;
	}

	private static TippedDart changeTippedDart( TippedDart dart ){
		TippedDart n;
		do {
			n = TippedDart.randomTipped(1);
		} while (n.getClass() == dart.getClass());

		return n;
	}
	
	private static Weapon changeWeapon( Weapon w ) {
		Weapon n;
		Generator.Category c;
		if (w instanceof MeleeWeapon) {
			c = Generator.wepTiers[((MeleeWeapon)w).tier - 1];
		} else {
			c = Generator.misTiers[((MissileWeapon)w).tier - 1];
		}
		
		do {
			n = (Weapon)Generator.randomUsingDefaults(c);
		} while (Challenges.isItemBlocked(n) || n.getClass() == w.getClass());

		n.等级(0);
		n.数量(w.数量());
		int level = w.真等级();
		if (level > 0) {
			n.升级( level );
		} else if (level < 0) {
			n.降级( -level );
		}
		
		n.enchantment = w.enchantment;
		n.curseInfusionBonus = w.curseInfusionBonus;
		n.masteryPotionBonus = w.masteryPotionBonus;
		n.神力 = w.神力;
		n.levelKnown = w.levelKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;
		n.augment = w.augment;
		n.enchantHardened = w.enchantHardened;

		//technically a new set, ensure old one is destroyed (except for darts)
		if (w instanceof MissileWeapon && w.可升级()){
			Buff.施加(Dungeon.hero, MissileWeapon.UpgradedSetTracker.class).levelThresholds.put(((MissileWeapon) w).setID, Integer.MAX_VALUE);
			//also extra missile weapon properties
			((MissileWeapon) n).damage(100 - ((MissileWeapon)w).durabilityLeft());
		}
		
		return n;
		
	}
	
	private static Ring changeRing( Ring r ) {
		Ring n;
		do {
			n = (Ring)Generator.randomUsingDefaults( Generator.Category.RING );
		} while (Challenges.isItemBlocked(n) || n.getClass() == r.getClass());
		
		n.等级(0);
		
		int level = r.等级();
		if (level > 0) {
			n.升级( level );
		} else if (level < 0) {
			n.降级( -level );
		}
		
		n.levelKnown = r.levelKnown;
		n.cursedKnown = r.cursedKnown;
		n.cursed = r.cursed;
		
		return n;
	}
	
	private static Artifact changeArtifact( Artifact a ) {
		Artifact n;
		do {
			n = Generator.randomArtifact();
		} while ( n != null && (Challenges.isItemBlocked(n) || n.getClass() == a.getClass()));
		
		if (n != null){

			if (a instanceof DriedRose){
				if (((DriedRose) a).ghostWeapon() != null){
					Dungeon.level.drop(((DriedRose) a).ghostWeapon(), Dungeon.hero.pos);
				}
				if (((DriedRose) a).ghostArmor() != null){
					Dungeon.level.drop(((DriedRose) a).ghostArmor(), Dungeon.hero.pos);
				}
			}

			n.cursedKnown = a.cursedKnown;
			n.cursed = a.cursed;
			n.levelKnown = a.levelKnown;
			n.transferUpgrade(a.visiblyUpgraded());
			return n;
		}
		
		return null;
	}

	private static Trinket changeTrinket( Trinket t ){
		Trinket n;
		do {
			n = (Trinket)Generator.random(Generator.Category.TRINKET);
		} while ( Challenges.isItemBlocked(n) || n.getClass() == t.getClass());

		n.等级(t.真等级());
		n.levelKnown = t.levelKnown;
		n.cursedKnown = t.cursedKnown;
		n.cursed = t.cursed;

		return n;
	}
	
	private static Wand changeWand( Wand w ) {
		Wand n;
		do {
			n = (Wand)Generator.randomUsingDefaults( Generator.Category.WAND );
		} while ( Challenges.isItemBlocked(n) || n.getClass() == w.getClass());
		
		n.等级( 0 );
		int level = w.真等级();
		n.升级( level );

		n.levelKnown = w.levelKnown;
		n.curChargeKnown = w.curChargeKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;
		n.curseInfusionBonus = w.curseInfusionBonus;
		n.resinBonus = w.resinBonus;

		n.curCharges =  w.curCharges;
		n.updateLevel();
		
		return n;
	}
	
	private static Plant.Seed changeSeed( Plant.Seed s ) {
		Plant.Seed n;
		
		do {
			n = (Plant.Seed)Generator.randomUsingDefaults( Generator.Category.SEED );
		} while (n.getClass() == s.getClass());
		
		return n;
	}
	
	private static Runestone changeStone( Runestone r ) {
		Runestone n;
		
		do {
			n = (Runestone) Generator.randomUsingDefaults( Generator.Category.STONE );
		} while (n.getClass() == r.getClass());
		
		return n;
	}

	private static Scroll changeScroll( Scroll s ) {
		if (s instanceof ExoticScroll) {
			return Reflection.newInstance(ExoticScroll.exoToReg.get(s.getClass()));
		} else {
			return Reflection.newInstance(ExoticScroll.regToExo.get(s.getClass()));
		}
	}

	private static Potion changePotion( Potion p ) {
		if	(p instanceof ExoticPotion) {
			return Reflection.newInstance(ExoticPotion.exoToReg.get(p.getClass()));
		} else {
			return Reflection.newInstance(ExoticPotion.regToExo.get(p.getClass()));
		}
	}
	
	@Override
	public int 金币() {
		return isKnown() ? 50 * quantity : super.金币();
	}

	@Override
	public int 能量() {
		return isKnown() ? 10 * quantity : super.能量();
	}
}

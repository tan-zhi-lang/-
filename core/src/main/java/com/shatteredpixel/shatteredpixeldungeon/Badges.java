

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.宝物袋;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.绒布袋;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.易爆;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镐子;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Badges {

	public enum BadgeType {
		HIDDEN, //internal badges used for data tracking
		LOCAL,  //unlocked on a per-run basis and added to overall player profile
		GLOBAL, //unlocked for the save profile only, usually over multiple runs
		JOURNAL //profile-based and also tied to the journal, which means they even unlock in seeded runs
	}

	public enum Badge {
		//bronze

		//region 解锁英雄
		解锁战士                 ( 0 ),
		UNLOCK_MAGE                 ( 1 ),
		解锁盗贼( 2 ),
		UNLOCK_HUNTRESS             ( 3 ),
		UNLOCK_DUELIST              ( 4 ),
		UNLOCK_CLERIC               ( 5 ),
		解锁巫女               ( 6 ),
		解锁重武               ( 7 ),
		解锁镜魔               ( 8 ),
		解锁道士               ( 9 ),
		解锁行僧               ( 10 ),
		解锁近卫               ( 11 ),
		解锁兽灵               ( 12 ),
		解锁机器               ( 13 ),
		解锁女忍               ( 14 ),
		解锁戒老               ( 15 ),
		解锁逐姝               ( 16 ),
		解锁罗兰               ( 17 ),
		解锁学士               ( 18 ),
		解锁灵猫               ( 19 ),
		解锁鼠弟               ( 20 ),
		解锁凌云               ( 21 ),
		解锁血鬼               ( 22 ),
		解锁来世(23 ),
		//endregion
		//黄铜
		MONSTERS_SLAIN_1(32),
		MONSTERS_SLAIN_2(33),
		GOLD_COLLECTED_1(34),
		GOLD_COLLECTED_2(27),
		ITEM_LEVEL_1(28),
		LEVEL_REACHED_1(29),
		STRENGTH_ATTAINED_1(30),
		FOOD_EATEN_1(31),
		ITEMS_CRAFTED_1(32),
		BOSS_SLAIN_1(33),
		CATALOG_ONE_EQUIPMENT(34, BadgeType.JOURNAL),
		DEATH_FROM_FIRE(35),
		DEATH_FROM_POISON(36),
		DEATH_FROM_GAS(37),
		DEATH_FROM_HUNGER(38),
		DEATH_FROM_FALLING(39),
		RESEARCHER_1(40, BadgeType.JOURNAL),
		GAMES_PLAYED_1(41, BadgeType.GLOBAL),
		HIGH_SCORE_1(50),

		//silver 白银
		NO_MONSTERS_SLAIN           (64),
		BOSS_SLAIN_REMAINS          (65),
		MONSTERS_SLAIN_3            (66),
		MONSTERS_SLAIN_4            (67),
		GOLD_COLLECTED_3            (68),
		GOLD_COLLECTED_4            (69),
		ITEM_LEVEL_2                (70),
		ITEM_LEVEL_3                (71),
		LEVEL_REACHED_2             (72),
		LEVEL_REACHED_3             (73),
		STRENGTH_ATTAINED_2         (74),
		STRENGTH_ATTAINED_3         (75),
		FOOD_EATEN_2                (76),
		FOOD_EATEN_3                (77),
		ITEMS_CRAFTED_2             (78),
		ITEMS_CRAFTED_3             (79),
		BOSS_SLAIN_2                (80),
		BOSS_SLAIN_3                (81),
		ALL_POTIONS_IDENTIFIED      , // still exists internally for pre-2.5 saves
		ALL_SCROLLS_IDENTIFIED      , // still exists internally for pre-2.5 saves
		CATALOG_POTIONS_SCROLLS     (82),
		DEATH_FROM_ENEMY_MAGIC      (83),
		DEATH_FROM_FRIENDLY_MAGIC   (84),
		DEATH_FROM_SACRIFICE        (85),
		RESEARCHER_2                (86, BadgeType.JOURNAL),
		GAMES_PLAYED_2              (87, BadgeType.GLOBAL),
		HIGH_SCORE_2                (88),

		//gold 黄金
		ENEMY_HAZARDS               (104),
		PIRANHAS                    (105),
		GRIM_WEAPON                 (106),
		BAG_BOUGHT_VELVET_POUCH,
		BAG_BOUGHT_SCROLL_HOLDER,
		BAG_BOUGHT_POTION_BANDOLIER,
		BAG_BOUGHT_MAGICAL_HOLSTER,
		BAG_杂物袋,
		ALL_BAGS_BOUGHT             (107),
		MASTERY_COMBO               (108),
		MONSTERS_SLAIN_5            (109),
		GOLD_COLLECTED_5            (110),
		ITEM_LEVEL_4                (111),
		LEVEL_REACHED_4             (112),
		STRENGTH_ATTAINED_4         (113),
		STRENGTH_ATTAINED_5         (114),
		FOOD_EATEN_4                (115),
		FOOD_EATEN_5                (116),
		ITEMS_CRAFTED_4             (117),
		ITEMS_CRAFTED_5             (118),
		BOSS_SLAIN_4                (119),
		ALL_RINGS_IDENTIFIED        , // still exists internally for pre-2.5 saves
		ALL_ARTIFACTS_IDENTIFIED    , // still exists internally for pre-2.5 saves
		ALL_RARE_ENEMIES            (120, BadgeType.JOURNAL), // no longer all, just 10 as of v3.1
		DEATH_FROM_GRIM_TRAP        (121), // also disintegration traps
		VICTORY                     (122),
		BOSS_CHALLENGE_1            (123),
		BOSS_CHALLENGE_2            (124),
		RESEARCHER_3                (125, BadgeType.JOURNAL),
		GAMES_PLAYED_3              (126, BadgeType.GLOBAL),
		HIGH_SCORE_3                (127),

		//platinum 铂金
		MANY_BUFFS                  (136),
		ITEM_LEVEL_5                (137),
		LEVEL_REACHED_5             (138),
		HAPPY_END                   (139),
		VICTORY_RANDOM              (140),
		HAPPY_END_REMAINS           (141),
		RODNEY                      (142, BadgeType.JOURNAL),
		ALL_WEAPONS_IDENTIFIED      , // still exists internally for pre-2.5 saves
		ALL_ARMOR_IDENTIFIED        , // still exists internally for pre-2.5 saves
		ALL_WANDS_IDENTIFIED        , // still exists internally for pre-2.5 saves
		ALL_ITEMS_IDENTIFIED        , // still exists internally for pre-2.5 saves
		DEATH_FROM_ALL              (143, BadgeType.GLOBAL),
		BOSS_CHALLENGE_3            (144),
		BOSS_CHALLENGE_4            (145),
		RESEARCHER_4                (146, BadgeType.JOURNAL),
		GAMES_PLAYED_4              (147, BadgeType.GLOBAL),
		HIGH_SCORE_4                (148),
		CHAMPION_1                  (149),

		// diamond 钻石
		PACIFIST_ASCENT             (160),
		TAKING_THE_MICK             (161), // This might be the most obscure game reference I've made
		BOSS_CHALLENGE_5            (162),
		RESEARCHER_5                (163, BadgeType.JOURNAL),
		GAMES_PLAYED_5              (164, BadgeType.GLOBAL),
		HIGH_SCORE_5                (165),
		CHAMPION_2                  (166),
		CHAMPION_3                  (167);

		public boolean meta;

		public int image;
		public BadgeType type;

		Badge(){
			this(-1, BadgeType.HIDDEN);
		}

		Badge( int image ) {
			this( image, BadgeType.LOCAL );
		}

		Badge( int image, BadgeType type ) {
			this.image = image;
			this.type = type;
		}

		public String title(){
			return Messages.get(this, name()+".title");
		}

		public String desc(){
			return Messages.get(this, name()+".desc");
		}
	}
	
	private static HashSet<Badge> global;
	public static HashSet<Badge> local = new HashSet<>();
	
	private static boolean saveNeeded = false;

	public static void reset() {
		local.clear();
		loadGlobal();
	}
	
	public static final String BADGES_FILE	= "badges.dat";
	private static final String BADGES		= "badges";
	
	private static final HashSet<String> removedBadges = new HashSet<>();
	static{
		//no removed badges currently
	}

	private static final HashMap<String, String> renamedBadges = new HashMap<>();
	static{
		//no renamed badges currently
	}

	public static HashSet<Badge> restore( Bundle bundle ) {
		HashSet<Badge> badges = new HashSet<>();
		if (bundle == null) return badges;
		
		String[] names = bundle.getStringArray( BADGES );
		if (names == null) return badges;

		for (int i=0; i < names.length; i++) {
			try {
				if (renamedBadges.containsKey(names[i])){
					names[i] = renamedBadges.get(names[i]);
				}
				if (!removedBadges.contains(names[i])){
					badges.add( Badge.valueOf( names[i] ) );
				}
			} catch (Exception e) {
				ShatteredPixelDungeon.reportException(e);
			}
		}

		addReplacedBadges(badges);
	
		return badges;
	}
	
	public static void store( Bundle bundle, HashSet<Badge> badges ) {
		addReplacedBadges(badges);

		int count = 0;
		String names[] = new String[badges.size()];
		
		for (Badge badge:badges) {
			names[count++] = badge.name();
		}
		bundle.put( BADGES, names );
	}
	
	public static void loadLocal( Bundle bundle ) {
		local = restore( bundle );
	}
	
	public static void saveLocal( Bundle bundle ) {
		store( bundle, local );
	}
	
	public static void loadGlobal() {
		if (global == null) {
			try {
				Bundle bundle = FileUtils.bundleFromFile( BADGES_FILE );
				global = restore( bundle );

			} catch (IOException e) {
				global = new HashSet<>();
			}
		}
	}

	public static void saveGlobal(){
		saveGlobal(false);
	}

	public static void saveGlobal(boolean force) {
		if (saveNeeded || force) {
			
			Bundle bundle = new Bundle();
			store( bundle, global );
			
			try {
				FileUtils.bundleToFile(BADGES_FILE, bundle);
				saveNeeded = false;
			} catch (IOException e) {
				ShatteredPixelDungeon.reportException(e);
			}
		}
	}

	public static int totalUnlocked(boolean global){
		if (global) return Badges.global.size();
		else        return Badges.local.size();
	}

	public static void validateMonstersSlain() {
		Badge badge = null;
		
		if (!local.contains( Badge.MONSTERS_SLAIN_1 ) && Statistics.enemiesSlain >= 10) {
			badge = Badge.MONSTERS_SLAIN_1;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_2 ) && Statistics.enemiesSlain >= 50) {
			if (badge != null) unlock(badge);
			badge = Badge.MONSTERS_SLAIN_2;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_3 ) && Statistics.enemiesSlain >= 100) {
			if (badge != null) unlock(badge);
			badge = Badge.MONSTERS_SLAIN_3;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_4 ) && Statistics.enemiesSlain >= 250) {
			if (badge != null) unlock(badge);
			badge = Badge.MONSTERS_SLAIN_4;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_5 ) && Statistics.enemiesSlain >= 500) {
			if (badge != null) unlock(badge);
			badge = Badge.MONSTERS_SLAIN_5;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateGoldCollected() {
		Badge badge = null;
		
		if (!local.contains( Badge.GOLD_COLLECTED_1 ) && Statistics.goldCollected >= 50) {
			if (badge != null) unlock(badge);
			badge = Badge.GOLD_COLLECTED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_2 ) && Statistics.goldCollected >= 200) {
			if (badge != null) unlock(badge);
			badge = Badge.GOLD_COLLECTED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_3 ) && Statistics.goldCollected >= 500) {
			if (badge != null) unlock(badge);
			badge = Badge.GOLD_COLLECTED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_4 ) && Statistics.goldCollected >= 1500) {
			if (badge != null) unlock(badge);
			badge = Badge.GOLD_COLLECTED_4;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_5 ) && Statistics.goldCollected >= 3000) {
			if (badge != null) unlock(badge);
			badge = Badge.GOLD_COLLECTED_5;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateLevelReached() {
		Badge badge = null;
		
		if (!local.contains( Badge.LEVEL_REACHED_1 ) && Dungeon.hero.等级 >= 2) {
			badge = Badge.LEVEL_REACHED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_2 ) && Dungeon.hero.等级 >= 6) {
			if (badge != null) unlock(badge);
			badge = Badge.LEVEL_REACHED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_3 ) && Dungeon.hero.等级 >= 11) {
			if (badge != null) unlock(badge);
			badge = Badge.LEVEL_REACHED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_4 ) && Dungeon.hero.等级 >= 21) {
			if (badge != null) unlock(badge);
			badge = Badge.LEVEL_REACHED_4;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_5 ) && Dungeon.hero.等级 >= 25) {
			if (badge != null) unlock(badge);
			badge = Badge.LEVEL_REACHED_5;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateStrengthAttained() {
		Badge badge = null;
		
		if (!local.contains( Badge.STRENGTH_ATTAINED_1 ) && Dungeon.hero.力量() >= 12) {
			badge = Badge.STRENGTH_ATTAINED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_2 ) && Dungeon.hero.力量() >= 14) {
			if (badge != null) unlock(badge);
			badge = Badge.STRENGTH_ATTAINED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_3 ) && Dungeon.hero.力量() >= 16) {
			if (badge != null) unlock(badge);
			badge = Badge.STRENGTH_ATTAINED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_4 ) && Dungeon.hero.力量() >= 18) {
			if (badge != null) unlock(badge);
			badge = Badge.STRENGTH_ATTAINED_4;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_5 ) && Dungeon.hero.力量() >= 20) {
			if (badge != null) unlock(badge);
			badge = Badge.STRENGTH_ATTAINED_5;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateFoodEaten() {
		Badge badge = null;
		
		if (!local.contains( Badge.FOOD_EATEN_1 ) && Statistics.foodEaten >= 10) {
			badge = Badge.FOOD_EATEN_1;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_2 ) && Statistics.foodEaten >= 20) {
			if (badge != null) unlock(badge);
			badge = Badge.FOOD_EATEN_2;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_3 ) && Statistics.foodEaten >= 30) {
			if (badge != null) unlock(badge);
			badge = Badge.FOOD_EATEN_3;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_4 ) && Statistics.foodEaten >= 40) {
			if (badge != null) unlock(badge);
			badge = Badge.FOOD_EATEN_4;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_5 ) && Statistics.foodEaten >= 50) {
			if (badge != null) unlock(badge);
			badge = Badge.FOOD_EATEN_5;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateItemsCrafted() {
		Badge badge = null;
		
		if (!local.contains( Badge.ITEMS_CRAFTED_1 ) && Statistics.itemsCrafted >= 3) {
			badge = Badge.ITEMS_CRAFTED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEMS_CRAFTED_2 ) && Statistics.itemsCrafted >= 8) {
			if (badge != null) unlock(badge);
			badge = Badge.ITEMS_CRAFTED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEMS_CRAFTED_3 ) && Statistics.itemsCrafted >= 15) {
			if (badge != null) unlock(badge);
			badge = Badge.ITEMS_CRAFTED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEMS_CRAFTED_4 ) && Statistics.itemsCrafted >= 24) {
			if (badge != null) unlock(badge);
			badge = Badge.ITEMS_CRAFTED_4;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEMS_CRAFTED_5 ) && Statistics.itemsCrafted >= 35) {
			if (badge != null) unlock(badge);
			badge = Badge.ITEMS_CRAFTED_5;
			local.add( badge );
		}
		
		displayBadge( badge );
	}

	public static void validateHazardAssists() {
		if (!local.contains( Badge.ENEMY_HAZARDS ) && Statistics.hazardAssistedKills >= 10) {
			local.add( Badge.ENEMY_HAZARDS );
			displayBadge( Badge.ENEMY_HAZARDS );
		}
	}
	
	public static void validatePiranhasKilled() {
		Badge badge = null;
		
		if (!local.contains( Badge.PIRANHAS ) && Statistics.piranhasKilled >= 6) {
			badge = Badge.PIRANHAS;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateItemLevelAquired( Item item ) {
		
		// This method should be called:
		// 1) When an item is obtained (Item.collect)
		// 2) When an item is upgraded (ScrollOfUpgrade, ScrollOfWeaponUpgrade, ShortSword, WandOfMagicMissile)
		// 3) When an item is identified

		// Note that artifacts should never trigger this badge as they are alternatively upgraded
		if (!item.levelKnown || item instanceof Artifact) {
			return;
		}

		if (item instanceof Weapon){
			validateDuelistUnlock();
		}
		
		Badge badge = null;
		if (!local.contains( Badge.ITEM_LEVEL_1 ) && item.等级() >= 3) {
			badge = Badge.ITEM_LEVEL_1;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_2 ) && item.等级() >= 6) {
			if (badge != null) unlock(badge);
			badge = Badge.ITEM_LEVEL_2;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_3 ) && item.等级() >= 9) {
			if (badge != null) unlock(badge);
			badge = Badge.ITEM_LEVEL_3;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_4 ) && item.等级() >= 12) {
			if (badge != null) unlock(badge);
			badge = Badge.ITEM_LEVEL_4;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_5 ) && item.等级() >= 15) {
			if (badge != null) unlock(badge);
			badge = Badge.ITEM_LEVEL_5;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateAllBagsBought( Item bag ) {
		
		Badge badge = null;
		if (bag instanceof 绒布袋) {
			badge = Badge.BAG_BOUGHT_VELVET_POUCH;
		} else if (bag instanceof ScrollHolder) {
			badge = Badge.BAG_BOUGHT_SCROLL_HOLDER;
		} else if (bag instanceof PotionBandolier) {
			badge = Badge.BAG_BOUGHT_POTION_BANDOLIER;
		} else if (bag instanceof MagicalHolster) {
			badge = Badge.BAG_BOUGHT_MAGICAL_HOLSTER;
		}else if (bag instanceof 宝物袋) {
			badge = Badge.BAG_杂物袋;
		}
		
		if (badge != null) {
			
			local.add( badge );
			
			if (!local.contains( Badge.ALL_BAGS_BOUGHT ) &&
				local.contains( Badge.BAG_BOUGHT_VELVET_POUCH ) &&
				local.contains( Badge.BAG_BOUGHT_SCROLL_HOLDER ) &&
				local.contains( Badge.BAG_BOUGHT_POTION_BANDOLIER ) &&
				local.contains( Badge.BAG_BOUGHT_MAGICAL_HOLSTER )&&
				local.contains( Badge.BAG_杂物袋 )) {
						
					badge = Badge.ALL_BAGS_BOUGHT;
					local.add( badge );
					displayBadge( badge );
			}
		}
	}

	//several badges all tie into catalog completion
	public static void validateCatalogBadges(){

		int totalSeen = 0;
		int totalThings = 0;

		for (Catalog cat : Catalog.values()){
			totalSeen += cat.totalSeen();
			totalThings += cat.totalItems();
		}

		for (Bestiary cat : Bestiary.values()){
			totalSeen += cat.totalSeen();
			totalThings += cat.totalEntities();
		}

		for (Document doc : Document.values()){
			if (!doc.isLoreDoc()) {
				for (String page : doc.pageNames()){
					if (doc.isPageFound(page)) totalSeen++;
					totalThings++;
				}
			}
		}

		//overall unlock badges
		Badge badge = null;
		if (totalSeen >= 40) {
			badge = Badge.RESEARCHER_1;
		}
		if (totalSeen >= 80) {
			unlock(badge);
			badge = Badge.RESEARCHER_2;
		}
		if (totalSeen >= 160) {
			unlock(badge);
			badge = Badge.RESEARCHER_3;
		}
		if (totalSeen >= 320) {
			unlock(badge);
			badge = Badge.RESEARCHER_4;
		}
		if (totalSeen == totalThings) {
			unlock(badge);
			badge = Badge.RESEARCHER_5;
		}
		displayBadge( badge );

		//specific task badges

		boolean qualified = true;
		for (Catalog cat : Catalog.equipmentCatalogs) {
			if (cat != Catalog.ENCHANTMENTS && cat != Catalog.GLYPHS) {
				if (cat.totalSeen() == 0) {
					qualified = false;
					break;
				}
			}
		}
		if (qualified) {
			displayBadge(Badge.CATALOG_ONE_EQUIPMENT);
		}

		//doesn't actually use catalogs, but triggers at the same time effectively
		if (!local.contains(Badge.CATALOG_POTIONS_SCROLLS)
				&& Potion.allKnown() && Scroll.allKnown()
				&& Dungeon.hero() && Dungeon.hero.isAlive()){
			local.add(Badge.CATALOG_POTIONS_SCROLLS);
			displayBadge(Badge.CATALOG_POTIONS_SCROLLS);
		}

		if (Bestiary.RARE.totalSeen() >= 10){
			displayBadge(Badge.ALL_RARE_ENEMIES);
		}

		if (Document.HALLS_KING.isPageRead(Document.KING_ATTRITION)){
			displayBadge(Badge.RODNEY);
		}

	}
	
	public static void validateDeathFromFire() {
		Badge badge = Badge.DEATH_FROM_FIRE;
		local.add( badge );
		displayBadge( badge );
		
		validateDeathFromAll();
	}
	
	public static void validateDeathFromPoison() {
		Badge badge = Badge.DEATH_FROM_POISON;
		local.add( badge );
		displayBadge( badge );
		
		validateDeathFromAll();
	}
	
	public static void validateDeathFromGas() {
		Badge badge = Badge.DEATH_FROM_GAS;
		local.add( badge );
		displayBadge( badge );
		
		validateDeathFromAll();
	}
	
	public static void validateDeathFromHunger() {
		Badge badge = Badge.DEATH_FROM_HUNGER;
		local.add( badge );
		displayBadge( badge );
		
		validateDeathFromAll();
	}

	public static void validateDeathFromFalling() {
		Badge badge = Badge.DEATH_FROM_FALLING;
		local.add( badge );
		displayBadge( badge );

		validateDeathFromAll();
	}

	public static void validateDeathFromEnemyMagic() {
		Badge badge = Badge.DEATH_FROM_ENEMY_MAGIC;
		local.add( badge );
		displayBadge( badge );

		validateDeathFromAll();
	}
	
	public static void validateDeathFromFriendlyMagic() {
		Badge badge = Badge.DEATH_FROM_FRIENDLY_MAGIC;
		local.add( badge );
		displayBadge( badge );

		validateDeathFromAll();
	}

	public static void validateDeathFromSacrifice() {
		Badge badge = Badge.DEATH_FROM_SACRIFICE;
		local.add( badge );
		displayBadge( badge );

		validateDeathFromAll();
	}

	public static void validateDeathFromGrimOrDisintTrap() {
		Badge badge = Badge.DEATH_FROM_GRIM_TRAP;
		local.add( badge );
		displayBadge( badge );

		validateDeathFromAll();
	}
	
	private static void validateDeathFromAll() {
		if (isUnlocked( Badge.DEATH_FROM_FIRE ) &&
				isUnlocked( Badge.DEATH_FROM_POISON ) &&
				isUnlocked( Badge.DEATH_FROM_GAS ) &&
				isUnlocked( Badge.DEATH_FROM_HUNGER) &&
				isUnlocked( Badge.DEATH_FROM_FALLING) &&
				isUnlocked( Badge.DEATH_FROM_ENEMY_MAGIC) &&
				isUnlocked( Badge.DEATH_FROM_FRIENDLY_MAGIC) &&
				isUnlocked( Badge.DEATH_FROM_SACRIFICE) &&
				isUnlocked( Badge.DEATH_FROM_GRIM_TRAP)) {

			Badge badge = Badge.DEATH_FROM_ALL;
			if (!isUnlocked( badge )) {
				displayBadge( badge );
			}
		}
	}

	public static void validateBossSlain() {
		Badge badge = null;
		switch (Dungeon.相对层数()) {
		case 5:
			badge = Badge.BOSS_SLAIN_1;
			break;
		case 10:
			badge = Badge.BOSS_SLAIN_2;
			break;
		case 15:
			badge = Badge.BOSS_SLAIN_3;
			break;
		case 20:
			badge = Badge.BOSS_SLAIN_4;
			break;
		}
		
		if (badge != null) {
			local.add( badge );
			displayBadge( badge );
			
			if (badge == Badge.BOSS_SLAIN_1) {
				local.add( badge );
				unlock(badge);

			} else if (badge == Badge.BOSS_SLAIN_3) {
				local.add( badge );
				unlock(badge);
			}

			if (Statistics.qualifiedForBossRemainsBadge){
				badge = Badge.BOSS_SLAIN_REMAINS;
				local.add( badge );
				displayBadge( badge );
			}

		}
	}

	public static void validateBossChallengeCompleted(){
		Badge badge = null;
		switch (Dungeon.相对层数()) {
			case 5:
				badge = Badge.BOSS_CHALLENGE_1;
				break;
			case 10:
				badge = Badge.BOSS_CHALLENGE_2;
				break;
			case 15:
				badge = Badge.BOSS_CHALLENGE_3;
				break;
			case 20:
				badge = Badge.BOSS_CHALLENGE_4;
				break;
			case 25:
				badge = Badge.BOSS_CHALLENGE_5;
				break;
		}

		if (badge != null) {
			local.add(badge);
			displayBadge(badge);
		}
	}
	

	
	public static void 解锁战士(){
		if (!isUnlocked(Badge.解锁战士)){
			displayBadge( Badge.解锁战士 );
		}
	}

	public static void validateMageUnlock(){
		if (!isUnlocked(Badge.UNLOCK_MAGE)){
			displayBadge( Badge.UNLOCK_MAGE );
		}
	}

	public static void validateRogueUnlock(){
		if (Statistics.sneakAttacks >= 5 && !isUnlocked(Badge.解锁盗贼)){
			displayBadge( Badge.解锁盗贼);
		}
	}
	
	public static void validateHuntressUnlock(){
		if (Statistics.thrownAttacks >= 2 && !isUnlocked(Badge.UNLOCK_HUNTRESS)){
			displayBadge( Badge.UNLOCK_HUNTRESS );
		}
	}

	public static void validateDuelistUnlock(){
		if (!isUnlocked(Badge.UNLOCK_DUELIST) && Dungeon.hero()
				&& Dungeon.hero.belongings.weapon instanceof Weapon
				&& (Dungeon.hero.belongings.weapon).tier() >= 2
				&& (Dungeon.hero.belongings.weapon).力量() <= Dungeon.hero.力量()){

			if (Dungeon.hero.belongings.weapon.已鉴定() &&
					(Dungeon.hero.belongings.weapon).力量() <= Dungeon.hero.力量()) {
				displayBadge(Badge.UNLOCK_DUELIST);

			} else if (!Dungeon.hero.belongings.weapon.已鉴定() &&
					(Dungeon.hero.belongings.weapon).力量(0) <= Dungeon.hero.力量()){
				displayBadge(Badge.UNLOCK_DUELIST);
			}
		}
	}

	public static void validateClericUnlock(){
		if (!isUnlocked(Badge.UNLOCK_CLERIC)){
			displayBadge( Badge.UNLOCK_CLERIC );
		}
	}
	public static void 解锁巫女(){
		if (!isUnlocked(Badge.解锁巫女)){
			displayBadge( Badge.解锁巫女 );
		}
	}
	public static void 解锁重武(){
		if (Statistics.thrownAttacks >= 10 &&!isUnlocked(Badge.解锁重武)){
			displayBadge( Badge.解锁重武 );
		}
	}
	public static void 解锁镜魔(){
		if (!isUnlocked(Badge.解锁镜魔)){
			displayBadge( Badge.解锁镜魔 );
		}
	}
	public static void 解锁道士(){
		if (!isUnlocked(Badge.解锁道士)){
			displayBadge( Badge.解锁道士 );
		}
	}
	public static void 解锁行僧(){
		if (!isUnlocked(Badge.解锁行僧)){
			displayBadge( Badge.解锁行僧 );
		}
	}
	public static void 解锁近卫(){
		if (!isUnlocked(Badge.解锁近卫)){
			displayBadge( Badge.解锁近卫 );
		}
	}
	public static void 解锁兽灵(){
		if (!isUnlocked(Badge.解锁兽灵)){
			displayBadge( Badge.解锁兽灵 );
		}
	}
	public static void 解锁机器(){
		if (!isUnlocked(Badge.解锁机器)){
			displayBadge( Badge.解锁机器 );
		}
	}
	public static void 解锁女忍(){
		if (!isUnlocked(Badge.解锁女忍)){
			displayBadge( Badge.解锁女忍 );
		}
	}
	public static void 解锁戒老(){
		if (!isUnlocked(Badge.解锁戒老)){
			displayBadge( Badge.解锁戒老 );
		}
	}
	public static void 解锁逐姝(){
		if (!isUnlocked(Badge.解锁逐姝)){
			displayBadge( Badge.解锁逐姝 );
		}
	}
	public static void 解锁罗兰(){
		if (!isUnlocked(Badge.解锁罗兰)){
			displayBadge( Badge.解锁罗兰 );
		}
	}
	public static void 解锁学士(){
		if (!isUnlocked(Badge.解锁学士)){
			displayBadge( Badge.解锁学士 );
		}
	}
	public static void 解锁灵猫(){
		if (!isUnlocked(Badge.解锁灵猫)){
			displayBadge( Badge.解锁灵猫 );
		}
	}
	public static void 解锁鼠弟(){
		if (!isUnlocked(Badge.解锁鼠弟)){
			displayBadge( Badge.解锁鼠弟 );
		}
	}
	public static void 解锁凌云(){
		if (!isUnlocked(Badge.解锁凌云)){
			displayBadge( Badge.解锁凌云 );
		}
	}
	public static void 解锁血鬼(){
		if (!isUnlocked(Badge.解锁血鬼)){
			displayBadge( Badge.解锁血鬼 );
		}
	}
	public static void 解锁来世(){
		if (!isUnlocked(Badge.解锁来世)){
			displayBadge( Badge.解锁来世);
		}
	}
	
	public static void validateMasteryCombo( int n ) {
		if (!local.contains( Badge.MASTERY_COMBO ) && n == 10) {
			Badge badge = Badge.MASTERY_COMBO;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateVictory() {

		Badge badge = Badge.VICTORY;
		local.add( badge );
		displayBadge( badge );
		
		//technically player can also not spend talent points if they want for some reason
		if (Statistics.qualifiedForRandomVictoryBadge
			&& Dungeon.hero.subClass != null){
			badge = Badge.VICTORY_RANDOM;
			local.add( badge );
			displayBadge( badge );
		}

		local.add( badge );
		unlock(badge);

	}

	public static void validateTakingTheMick(Object cause){
		if ((cause == Dungeon.hero || cause instanceof 易爆.ExplosiveCurseBomb)
			&&
				Dungeon.hero.belongings.weapon(镐子.class)
				&& Dungeon.hero.belongings.getItem(镐子.class).等级() >= 10){
			local.add( Badge.TAKING_THE_MICK );
			displayBadge(Badge.TAKING_THE_MICK);
		}
	}

	public static void validateNoKilling() {
		if (!local.contains( Badge.NO_MONSTERS_SLAIN ) && Statistics.completedWithNoKilling) {
			Badge badge = Badge.NO_MONSTERS_SLAIN;
			local.add( badge );
			displayBadge( badge );
			Statistics.completedWithNoKilling = false;
		}
	}
	
	public static void validateGrimWeapon() {
		if (!local.contains( Badge.GRIM_WEAPON )) {
			Badge badge = Badge.GRIM_WEAPON;
			local.add( badge );
			displayBadge( badge );
		}
	}

	public static void validateManyBuffs(){
		if (!local.contains( Badge.MANY_BUFFS )) {
			Badge badge = Badge.MANY_BUFFS;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateGamesPlayed() {
		Badge badge = null;
		if (Rankings.INSTANCE.totalNumber >= 10 || Rankings.INSTANCE.wonNumber >= 1) {
			badge = Badge.GAMES_PLAYED_1;
		}
		if (Rankings.INSTANCE.totalNumber >= 25 || Rankings.INSTANCE.wonNumber >= 3) {
			unlock(badge);
			badge = Badge.GAMES_PLAYED_2;
		}
		if (Rankings.INSTANCE.totalNumber >= 50 || Rankings.INSTANCE.wonNumber >= 5) {
			unlock(badge);
			badge = Badge.GAMES_PLAYED_3;
		}
		if (Rankings.INSTANCE.totalNumber >= 200 || Rankings.INSTANCE.wonNumber >= 10) {
			unlock(badge);
			badge = Badge.GAMES_PLAYED_4;
		}
		if (Rankings.INSTANCE.totalNumber >= 1000 || Rankings.INSTANCE.wonNumber >= 25) {
			unlock(badge);
			badge = Badge.GAMES_PLAYED_5;
		}
		
		displayBadge( badge );
	}

	public static void validateHighScore( int score ){
		Badge badge = null;
		if (score >= 5000) {
			badge = Badge.HIGH_SCORE_1;
			local.add( badge );
		}
		if (score >= 25_000) {
			unlock(badge);
			badge = Badge.HIGH_SCORE_2;
			local.add( badge );
		}
		if (score >= 100_000) {
			unlock(badge);
			badge = Badge.HIGH_SCORE_3;
			local.add( badge );
		}
		if (score >= 250_000) {
			unlock(badge);
			badge = Badge.HIGH_SCORE_4;
			local.add( badge );
		}
		if (score >= 1_000_000) {
			unlock(badge);
			badge = Badge.HIGH_SCORE_5;
			local.add( badge );
		}

		displayBadge( badge );
	}
	
	public static void validateHappyEnd() {
		local.add( Badge.HAPPY_END );
		displayBadge( Badge.HAPPY_END );

		if( Statistics.qualifiedForBossRemainsBadge){
			local.add( Badge.HAPPY_END_REMAINS );
			displayBadge( Badge.HAPPY_END_REMAINS );
		}

		if (AscensionChallenge.qualifiedForPacifist()) {
			local.add( Badge.PACIFIST_ASCENT );
			displayBadge( Badge.PACIFIST_ASCENT );
		}
	}

	public static void validateChampion( int challenges ) {
		if (challenges == 0) return;
		Badge badge = null;
		if (challenges >= 1) {
			badge = Badge.CHAMPION_1;
		}
		if (challenges >= 3){
			unlock(badge);
			badge = Badge.CHAMPION_2;
		}
		if (challenges >= 6){
			unlock(badge);
			badge = Badge.CHAMPION_3;
		}
		local.add(badge);
		displayBadge( badge );
	}
	
	public static void displayBadge(Badge badge) {

		if (badge == null ||badge.type == BadgeType.JOURNAL
//							  && !Dungeon.customSeedText.isEmpty()
							  //种子不能解锁勋章
		) {
			return;
		}
		
		if (isUnlocked( badge )) {
			
			if (badge.type == BadgeType.LOCAL) {
				GLog.黄(Messages.get(Badges.class,"endorsed",badge.title()));
				GLog.newLine();
			}
			
		} else {
			
			unlock(badge);
			
			GLog.黄(Messages.get(Badges.class,"new",badge.title()+" ("+badge.desc()+")"));
			GLog.newLine();
			PixelScene.showBadge( badge );
		}
	}
	
	public static boolean isUnlocked( Badge badge ) {
		return global.contains( badge );
	}
	
	public static HashSet<Badge> allUnlocked(){
		loadGlobal();
		return new HashSet<>(global);
	}
	
	public static void disown( Badge badge ) {
		loadGlobal();
		global.remove( badge );
		saveNeeded = true;
	}
	
	public static void unlock( Badge badge ){
		if (!isUnlocked(badge)
//			&& badge.type != BadgeType.JOURNAL
//								   || Dungeon.customSeedText.isEmpty()
								   //种子不能解锁勋章
		){
			global.add( badge );
			saveNeeded = true;
		}
	}

	public static List<Badge> filterReplacedBadges( boolean global ) {

		ArrayList<Badge> badges = new ArrayList<>(global ? Badges.global : Badges.local);

		Iterator<Badge> iterator = badges.iterator();
		while (iterator.hasNext()) {
			Badge badge = iterator.next();
			if ((!global && badge.type != BadgeType.LOCAL) || badge.type == BadgeType.HIDDEN) {
				iterator.remove();
			}
		}

		Collections.sort(badges);

		return filterReplacedBadges(badges);

	}

	//only show the highest unlocked and the lowest locked
	private static final Badge[][] tierBadgeReplacements = new Badge[][]{
			{Badge.MONSTERS_SLAIN_1, Badge.MONSTERS_SLAIN_2, Badge.MONSTERS_SLAIN_3, Badge.MONSTERS_SLAIN_4, Badge.MONSTERS_SLAIN_5},
			{Badge.GOLD_COLLECTED_1, Badge.GOLD_COLLECTED_2, Badge.GOLD_COLLECTED_3, Badge.GOLD_COLLECTED_4, Badge.GOLD_COLLECTED_5},
			{Badge.ITEM_LEVEL_1, Badge.ITEM_LEVEL_2, Badge.ITEM_LEVEL_3, Badge.ITEM_LEVEL_4, Badge.ITEM_LEVEL_5},
			{Badge.LEVEL_REACHED_1, Badge.LEVEL_REACHED_2, Badge.LEVEL_REACHED_3, Badge.LEVEL_REACHED_4, Badge.LEVEL_REACHED_5},
			{Badge.STRENGTH_ATTAINED_1, Badge.STRENGTH_ATTAINED_2, Badge.STRENGTH_ATTAINED_3, Badge.STRENGTH_ATTAINED_4, Badge.STRENGTH_ATTAINED_5},
			{Badge.FOOD_EATEN_1, Badge.FOOD_EATEN_2, Badge.FOOD_EATEN_3, Badge.FOOD_EATEN_4, Badge.FOOD_EATEN_5},
			{Badge.ITEMS_CRAFTED_1, Badge.ITEMS_CRAFTED_2, Badge.ITEMS_CRAFTED_3, Badge.ITEMS_CRAFTED_4, Badge.ITEMS_CRAFTED_5},
			{Badge.BOSS_SLAIN_1, Badge.BOSS_SLAIN_2, Badge.BOSS_SLAIN_3, Badge.BOSS_SLAIN_4},
			{Badge.RESEARCHER_1, Badge.RESEARCHER_2, Badge.RESEARCHER_3, Badge.RESEARCHER_4, Badge.RESEARCHER_5},
			{Badge.HIGH_SCORE_1, Badge.HIGH_SCORE_2, Badge.HIGH_SCORE_3, Badge.HIGH_SCORE_4, Badge.HIGH_SCORE_5},
			{Badge.GAMES_PLAYED_1, Badge.GAMES_PLAYED_2, Badge.GAMES_PLAYED_3, Badge.GAMES_PLAYED_4, Badge.GAMES_PLAYED_5},
			{Badge.CHAMPION_1, Badge.CHAMPION_2, Badge.CHAMPION_3}
	};

	//don't show the later badge if the earlier one isn't unlocked
	//we aren't too aggressive with this, mainly just want to prevent boss spoilers,
	// and all diamond tier badges must have a gold/plat prerequisite
	private static final Badge[][] prerequisiteBadges = new Badge[][]{
			{Badge.BOSS_SLAIN_1, Badge.BOSS_CHALLENGE_1},
			{Badge.BOSS_SLAIN_2, Badge.BOSS_CHALLENGE_2},
			{Badge.BOSS_SLAIN_3, Badge.BOSS_CHALLENGE_3},
			{Badge.BOSS_SLAIN_4, Badge.BOSS_CHALLENGE_4},
			{Badge.VICTORY,      Badge.BOSS_CHALLENGE_5},
			{Badge.HAPPY_END,    Badge.PACIFIST_ASCENT},
			{Badge.VICTORY,      Badge.TAKING_THE_MICK}
	};

	//If the summary badge is unlocked, don't show the component badges
	private static final Badge[][] summaryBadgeReplacements = new Badge[][]{
			{Badge.DEATH_FROM_FIRE, Badge.DEATH_FROM_ALL},
			{Badge.DEATH_FROM_GAS, Badge.DEATH_FROM_ALL},
			{Badge.DEATH_FROM_HUNGER, Badge.DEATH_FROM_ALL},
			{Badge.DEATH_FROM_POISON, Badge.DEATH_FROM_ALL},
			{Badge.DEATH_FROM_FALLING, Badge.DEATH_FROM_ALL},
			{Badge.DEATH_FROM_ENEMY_MAGIC, Badge.DEATH_FROM_ALL},
			{Badge.DEATH_FROM_FRIENDLY_MAGIC, Badge.DEATH_FROM_ALL},
			{Badge.DEATH_FROM_SACRIFICE, Badge.DEATH_FROM_ALL},
			{Badge.DEATH_FROM_GRIM_TRAP, Badge.DEATH_FROM_ALL},

			{Badge.ALL_WEAPONS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_ARMOR_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_WANDS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_RINGS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_ARTIFACTS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_POTIONS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_SCROLLS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED}
	};
	
	public static List<Badge> filterReplacedBadges( List<Badge> badges ) {

		for (Badge[] tierReplace : tierBadgeReplacements){
			leaveBest( badges, tierReplace );
		}

		for (Badge[] metaReplace : summaryBadgeReplacements){
			leaveBest( badges, metaReplace );
		}
		
		return badges;
	}
	
	private static void leaveBest( Collection<Badge> list, Badge...badges ) {
		for (int i=badges.length-1; i > 0; i--) {
			if (list.contains( badges[i])) {
				for (int j=0; j < i; j++) {
					list.remove( badges[j] );
				}
				break;
			}
		}
	}

	public static List<Badge> filterBadgesWithoutPrerequisites(List<Badges.Badge> badges ) {

		for (Badge[] prereqReplace : prerequisiteBadges){
			leaveWorst( badges, prereqReplace );
		}

		for (Badge[] tierReplace : tierBadgeReplacements){
			leaveWorst( badges, tierReplace );
		}

		Collections.sort( badges );

		return badges;
	}

	private static void leaveWorst( Collection<Badge> list, Badge...badges ) {
		for (int i=0; i < badges.length; i++) {
			if (list.contains( badges[i])) {
				for (int j=i+1; j < badges.length; j++) {
					list.remove( badges[j] );
				}
				break;
			}
		}
	}

	public static Collection<Badge> addReplacedBadges(Collection<Badges.Badge> badges ) {

		for (Badge[] tierReplace : tierBadgeReplacements){
			addLower( badges, tierReplace );
		}

		for (Badge[] metaReplace : summaryBadgeReplacements){
			addLower( badges, metaReplace );
		}

		return badges;
	}

	private static void addLower( Collection<Badge> list, Badge...badges ) {
		for (int i=badges.length-1; i > 0; i--) {
			if (list.contains( badges[i])) {
				for (int j=0; j < i; j++) {
					list.add( badges[j] );
				}
				break;
			}
		}
	}

	//used for badges with completion progress that would otherwise be hard to track
	public static String showCompletionProgress( Badge badge ){
		if (isUnlocked(badge)) return null;

		return null;
	}
}

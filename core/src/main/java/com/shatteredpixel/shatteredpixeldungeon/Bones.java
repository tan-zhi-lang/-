

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.remains.RemainsItem;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Bones {

	private static final String BONES_FILE	= "bones.dat";
	
	private static final String LEVEL	= "level";
	private static final String BRANCH	= "branch";
	private static final String ITEM	= "item";
	private static final String ITEM2=        "item2";
	private static final String ITEM3=        "item3";
	private static final String ITEM4=        "item4";
	private static final String ITEM5=        "item5";
	private static final String ITEM6=        "item6";
	private static final String ITEM7=        "item7";
	private static final String ITEM8=        "item8";
	private static final String ITEM9=        "item9";
	private static final String HERO_CLASS	= "hero_class";

	private static int depth = -1;
	private static int branch = -1;

	private static Item bone;
	
	public static int 金币;
	public static int 能量;
	public static Item item;
	public static Item item2;
	public static Item item3;
	public static Item item4;
	public static Item item5;
	public static Item item6;
	public static Item item7;
	public static Item item8;
	public static Item item9;
	public static ArrayList<Item> items = new ArrayList<>();
	private static HeroClass heroClass;

	public static void leave() {

		//remains will usually drop on the floor the hero died on
		// but are capped at 5 floors above the lowest depth reached (even when ascending)
		depth = Math.max(Dungeon.depth, Statistics.deepestFloor-5);

		branch = Dungeon.branch;

		//daily runs do not interact with remains
		if (Dungeon.daily) {
			depth = branch = -1;
			return;
		}
		
		金币=Dungeon.gold;
		能量=Dungeon.energy;
		bone = pickItem(Dungeon.hero);
		
		heroClass = Dungeon.hero.heroClass;

		Bundle bundle = new Bundle();
		bundle.put( LEVEL, depth );
		bundle.put( BRANCH, branch );
		if (item!=null) bundle.put(ITEM,item);
		if (item2!=null) bundle.put(ITEM2,item2);
		if (item3!=null) bundle.put(ITEM3,item3);
		if (item4!=null) bundle.put(ITEM4,item4);
		if (item5!=null) bundle.put(ITEM5,item5);
		if (item6!=null) bundle.put(ITEM6,item6);
		if (item7!=null) bundle.put(ITEM7,item7);
		if (item8!=null) bundle.put(ITEM8,item8);
		if (item9!=null) bundle.put(ITEM9,item9);
		bundle.put( HERO_CLASS, heroClass );

		try {
			FileUtils.bundleToFile( BONES_FILE, bundle );
		} catch (IOException e) {
			ShatteredPixelDungeon.reportException(e);
		}
	}

	private static Item pickItem(Hero hero){
		Item item = null;

		//seeded runs don't leave items
		//This is to prevent using specific seeds to transport items to regular runs
		if (!Dungeon.customSeedText.isEmpty()){
			return null;
		}

		if (Random.Int(3) != 0) {
			switch (Random.Int(7)) {
				case 0:
					item = hero.belongings.weapon;
					//if the hero has two weapons (champion), pick the stronger one
					if (hero.belongings.secondWep != null &&
							(item == null || hero.belongings.secondWep.真等级() > item.真等级())){
						item = hero.belongings.secondWep;
						break;
					}
					break;
				case 1:
					item = hero.belongings.armor;
					break;
				case 2:
					item = hero.belongings.misc;
					break;
				case 3:
					item = hero.belongings.misc2;
					break;
				case 4:
					item = hero.belongings.misc3;
					break;
				case 5: case 6:
					item = Dungeon.quickslot.randomNonePlaceholder();
					break;
			}
			if (item == null || !item.遗产) {
				return pickItem(hero);
			}
		} else {

			Iterator<Item> iterator = hero.belongings.backpack.iterator();
			Item curItem;
			ArrayList<Item> items = new ArrayList<>();
			while (iterator.hasNext()){
				curItem = iterator.next();
				if (curItem.遗产) {
					items.add(curItem);
				}
			}

			//if there are few items, there is an increasingly high chance of leaving nothing
			if (Random.Int(3) < items.size()) {
				item = Random.element(items);
				if (item.可堆叠){
					item.数量(Random.NormalIntRange(1,(item.数量()+1)/2));
					if (item.数量()>3){
						item.数量(3);
					}
				}
			} else {
				item = null;
			}
		}
		
		return item;
	}

	public static ArrayList<Item> get() {
		
		Badges.解锁来世();
		//daily runs do not interact with remains
		if (Dungeon.daily){
			return null;
		}

		if (depth == -1) {

			try {
				Bundle bundle = FileUtils.bundleFromFile(BONES_FILE);
//				items =new ArrayList<Item>((Collection<? extends Item>)bundle.getCollection(ITEMS));

				depth = bundle.getInt( LEVEL );
				branch = bundle.getInt( BRANCH );
				if (depth > 0) {
					if (bundle.contains(ITEM)) item= (Item)bundle.get(ITEM);
					if (bundle.contains(ITEM2)) item2= (Item)bundle.get(ITEM2);
					if (bundle.contains(ITEM3)) item3= (Item)bundle.get(ITEM3);
					if (bundle.contains(ITEM4)) item4= (Item)bundle.get(ITEM4);
					if (bundle.contains(ITEM5)) item5= (Item)bundle.get(ITEM5);
					if (bundle.contains(ITEM6)) item6= (Item)bundle.get(ITEM6);
					if (bundle.contains(ITEM7)) item7= (Item)bundle.get(ITEM7);
					if (bundle.contains(ITEM8)) item8= (Item)bundle.get(ITEM8);
					if (bundle.contains(ITEM9)) item9= (Item)bundle.get(ITEM9);
					if (bundle.contains(HERO_CLASS)){
						heroClass = bundle.getEnum(HERO_CLASS, HeroClass.class);
					} else {
						heroClass = null;
					}
				}

				return get();

			} catch (IOException e) {
				return null;
			}

		} else {
			if (lootAtCurLevel()) {

				Bundle emptyBones = new Bundle();
				emptyBones.put(LEVEL, 0);
				try {
					FileUtils.bundleToFile( BONES_FILE, emptyBones );
				} catch (IOException e) {
					ShatteredPixelDungeon.reportException(e);
				}
				depth = 0;

				//challenged or seeded runs don't get items from prior runs
				if (Dungeon.challenges != 0 || !Dungeon.customSeedText.isEmpty()){
					bone = null;
				}

				//Enforces artifact uniqueness
				if (bone instanceof Artifact){
					if (Generator.removeArtifact(((Artifact)bone).getClass())) {
						
						//generates a new artifact of the same type, always +0
						Artifact artifact = Reflection.newInstance(((Artifact)bone).getClass());
						
						if (artifact != null){
							artifact.cursed = true;
							artifact.cursedKnown = true;
						}
						
						bone = artifact;
						
					} else {
						bone = new Gold(bone.金币());
					}
				}

				if (bone != null) {
					if (bone.可升级() && !(bone instanceof Weapon)) {
						bone.cursed = true;
						bone.cursedKnown = true;
					}

					if (bone.可升级()) {
						//caps at +3
						if (bone.等级() > 3) {
							bone.降级(bone.等级() - 3);
						}
						//thrown weapons are always IDed, otherwise set unknown
						bone.levelKnown = bone instanceof Weapon;
					}
					
					bone.reset();
				}

				ArrayList<Item> result = new ArrayList<>();

				if (heroClass != null) {
					Item i=RemainsItem.get(heroClass);
					if(i!=null){
						result.add(i);
					}
					if (Dungeon.bossLevel()){
						Statistics.qualifiedForBossRemainsBadge = true;
					}
				}

				if (bone != null) {
					result.add(bone);
				}

				return result.isEmpty() ? null : result;
			} else {
				return null;
			}
		}
	}

	private static boolean lootAtCurLevel(){
		if (branch == Dungeon.branch) {
			if (branch == 0) {
				//always match depth exactly for main path
				return depth == Dungeon.depth;
			} else if (branch == 1) {
				//just match the region for quest sub-floors
				return depth/5 == Dungeon.depth/5;
			}
		}
		return false;
	}
}

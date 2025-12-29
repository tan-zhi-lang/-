

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.催眠卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.镜像卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.盛怒卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.复仇卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.恐惧卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.嬗变卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UnstableSpell extends Spell {

	{
		image = 物品表.UNSTABLE_SPELL;
		icon = 物品表.Icons.无序;
	}
	
	private static HashMap<Class<? extends Scroll>, Float> scrollChances = new HashMap<>();
	static{
		scrollChances.put( 鉴定卷轴.class,      3f );
		scrollChances.put( 祛邪卷轴.class,   2f );
		scrollChances.put( 探地卷轴.class,  2f );
		scrollChances.put(镜像卷轴.class,2f);
		scrollChances.put(充能卷轴.class,2f);
		scrollChances.put(催眠卷轴.class,2f);
		scrollChances.put(复仇卷轴.class,2f);
		scrollChances.put(盛怒卷轴.class,2f);
		scrollChances.put(传送卷轴.class,2f);
		scrollChances.put(恐惧卷轴.class,2f);
		scrollChances.put( 嬗变卷轴.class, 1f );
	}

	private static HashSet<Class<? extends Scroll>> nonCombatScrolls = new HashSet<>();
	static {
		nonCombatScrolls.add( 鉴定卷轴.class );
		nonCombatScrolls.add( 祛邪卷轴.class );
		nonCombatScrolls.add( 探地卷轴.class );
		nonCombatScrolls.add( 充能卷轴.class);
		nonCombatScrolls.add( 催眠卷轴.class);
		nonCombatScrolls.add( 传送卷轴.class);
		nonCombatScrolls.add( 嬗变卷轴.class );
	}

	private static HashSet<Class<? extends Scroll>> combatScrolls = new HashSet<>();
	static {
		combatScrolls.add( 镜像卷轴.class);
		combatScrolls.add( 充能卷轴.class);
		combatScrolls.add( 催眠卷轴.class);
		combatScrolls.add( 复仇卷轴.class);
		combatScrolls.add( 盛怒卷轴.class);
		combatScrolls.add( 传送卷轴.class);
		combatScrolls.add( 恐惧卷轴.class);
	}
	
	@Override
	protected void onCast(Hero hero) {
		
		detach( curUser.belongings.backpack );
		updateQuickslot();
		
		Scroll s = Reflection.newInstance(Random.chances(scrollChances));

		//reroll the scroll until it is relevant for the situation (whether there are visible enemies)
		if (hero.visibleEnemies() == 0){
			while (!nonCombatScrolls.contains(s.getClass())){
				s = Reflection.newInstance(Random.chances(scrollChances));
			}
		} else {
			while (!combatScrolls.contains(s.getClass())){
				s = Reflection.newInstance(Random.chances(scrollChances));
			}
		}

		s.anonymize();
		s.talentChance = s.talentFactor = 1;
		curItem = s;
		s.doRead();

		Catalog.countUse(getClass());
		//don't trigger talents, as they'll be triggered by the scroll
	}

	//lower values, as it's cheaper to make
	@Override
	public int 金币() {
		return 40 * quantity;
	}

	@Override
	public int 能量() {
		return 8 * quantity;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			boolean scroll = false;
			boolean stone = false;

			for (Item i : ingredients){
				if (i instanceof Runestone){
					stone = true;
					//if it is a regular or exotic potion
				} else if (ExoticScroll.regToExo.containsKey(i.getClass())
						|| ExoticScroll.regToExo.containsValue(i.getClass())) {
					scroll = true;
				}
			}

			return scroll && stone;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 1;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {

			for (Item i : ingredients){
				i.数量(i.数量()-1);
			}

			return sampleOutput(null);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new UnstableSpell();
		}
	}
}

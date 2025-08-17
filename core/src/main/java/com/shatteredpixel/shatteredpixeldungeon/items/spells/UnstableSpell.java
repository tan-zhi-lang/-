

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
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
	}
	
	private static HashMap<Class<? extends Scroll>, Float> scrollChances = new HashMap<>();
	static{
		scrollChances.put( 鉴定卷轴.class,      3f );
		scrollChances.put( 祛邪卷轴.class,   2f );
		scrollChances.put( 探地卷轴.class,  2f );
		scrollChances.put( ScrollOfMirrorImage.class,   2f );
		scrollChances.put( ScrollOfRecharging.class,    2f );
		scrollChances.put( ScrollOfLullaby.class,       2f );
		scrollChances.put( ScrollOfRetribution.class,   2f );
		scrollChances.put( ScrollOfRage.class,          2f );
		scrollChances.put( ScrollOfTeleportation.class, 2f );
		scrollChances.put( ScrollOfTerror.class,        2f );
		scrollChances.put( ScrollOfTransmutation.class, 1f );
	}

	private static HashSet<Class<? extends Scroll>> nonCombatScrolls = new HashSet<>();
	static {
		nonCombatScrolls.add( 鉴定卷轴.class );
		nonCombatScrolls.add( 祛邪卷轴.class );
		nonCombatScrolls.add( 探地卷轴.class );
		nonCombatScrolls.add( ScrollOfRecharging.class );
		nonCombatScrolls.add( ScrollOfLullaby.class );
		nonCombatScrolls.add( ScrollOfTeleportation.class );
		nonCombatScrolls.add( ScrollOfTransmutation.class );
	}

	private static HashSet<Class<? extends Scroll>> combatScrolls = new HashSet<>();
	static {
		combatScrolls.add( ScrollOfMirrorImage.class );
		combatScrolls.add( ScrollOfRecharging.class );
		combatScrolls.add( ScrollOfLullaby.class );
		combatScrolls.add( ScrollOfRetribution.class );
		combatScrolls.add( ScrollOfRage.class );
		combatScrolls.add( ScrollOfTeleportation.class );
		combatScrolls.add( ScrollOfTerror.class );
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
		curItem = s;
		s.doRead();
		Invisibility.dispel();

		Catalog.countUse(getClass());
		if (Random.Float() < talentChance){
			Talent.onScrollUsed(curUser, curUser.pos, talentFactor, getClass());
		}
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
				i.get数量(i.get数量()-1);
			}

			return sampleOutput(null);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new UnstableSpell();
		}
	}
}

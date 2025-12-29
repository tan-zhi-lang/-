

package com.shatteredpixel.shatteredpixeldungeon.items.potions.brews;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.隐形药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.浮空药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.液火药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.灵视药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.麻痹药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.毒气药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.净化药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.极速药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;

public class UnstableBrew extends Brew {

	{
		image = 物品表.BREW_UNSTABLE;
		icon = 物品表.Icons.紊乱;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_DRINK);
		return actions;
	}

	@Override
	public String defaultAction() {
		return AC_CHOOSE;
	}

	private static HashMap<Class<? extends Potion>, Float> potionChances = new HashMap<>();
	static {
		potionChances.put(治疗药剂.class, 3f);
		potionChances.put(灵视药剂.class,2f);
		potionChances.put(冰霜药剂.class,2f);
		potionChances.put(液火药剂.class,2f);
		potionChances.put(毒气药剂.class,2f);
		potionChances.put(极速药剂.class, 2f);
		potionChances.put(隐形药剂.class,2f);
		potionChances.put(浮空药剂.class,2f);
		potionChances.put(麻痹药剂.class,2f);
		potionChances.put(净化药剂.class, 2f);
		potionChances.put(经验药剂.class, 1f);
	}
	
	@Override
	public void apply(Hero hero) {
		//Don't allow this to roll healing in pharma
		if (Dungeon.isChallenged(Challenges.NO_HEALING)){
			potionChances.put(治疗药剂.class, 0f);
		}

		Potion p = Reflection.newInstance(Random.chances(potionChances));

		//reroll the potion if it wasn't a good potion to drink
		while (mustThrowPots.contains(p.getClass())){
			p = Reflection.newInstance(Random.chances(potionChances));
		}

		p.anonymize();
		p.apply(hero);

		if (Dungeon.isChallenged(Challenges.NO_HEALING)){
			potionChances.put(治疗药剂.class, 3f);
		}
	}
	
	@Override
	public void shatter(int cell) {
		Potion p = Reflection.newInstance(Random.chances(potionChances));

		//reroll the potion if it wasn't a good potion to throw
		while (!mustThrowPots.contains(p.getClass()) && !canThrowPots.contains(p.getClass())){
			p = Reflection.newInstance(Random.chances(potionChances));
		}

		p.anonymize();
		curItem = p;
		p.shatter(cell);
	}
	
	@Override
	public boolean isKnown() {
		return true;
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
			boolean potion = false;
			boolean seed = false;

			for (Item i : ingredients){
				if (i instanceof Plant.Seed) {
					seed = true;
					//if it is a regular or exotic potion
				} else if (ExoticPotion.regToExo.containsKey(i.getClass())
						|| ExoticPotion.regToExo.containsValue(i.getClass())) {
					potion = true;
				}
			}

			return potion && seed;
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
			return new UnstableBrew();
		}
	}
	
}

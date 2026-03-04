

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.RatSkull;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MobSpawner extends Actor {
	{
		actPriority = BUFF_PRIO; //as if it were a buff.
	}

	@Override
	protected boolean act() {

		if (Dungeon.level.mobCount() < Dungeon.level.mobLimit()) {

			if (Dungeon.level.spawnMob(12)){
				spend(Dungeon.level.respawnCooldown());
			} else {
				//try again in 1 turn
				spend(TICK);
			}

		} else {
			spend(Dungeon.level.respawnCooldown());
		}

		return true;
	}

	public void resetCooldown(){
		spend(-cooldown());
		spend(Dungeon.level.respawnCooldown());
	}

	public static ArrayList<Class<? extends Mob>> getMobRotation(int depth ){
		ArrayList<Class<? extends Mob>> mobs = standardMobRotation( depth );
		addRareMobs(depth, mobs);
		swapMobAlts(mobs);
		Random.shuffle(mobs);
		return mobs;
	}

	//returns a rotation of standard mobs, unshuffled.
	private static ArrayList<Class<? extends Mob>> standardMobRotation( int depth ){
		
		if(Dungeon.赛季(赛季设置.刷子地牢)&&depth>25){
			depth%=25;//解析：超过25求余即是循环层
		}
		if(Dungeon.赛季(赛季设置.鬼怨地牢)){
			return new ArrayList<>(Arrays.asList(
					鬼怨.class,
					鬼怨.class,
					鬼怨.class,
					鬼怨.class,
					
					鬼怨.class,
					鬼怨.class,
					鬼怨.class,
					鬼怨.class,
					
					仇鬼.class));
		}
		switch(depth){

			// Sewers
			case 1: default:
				//3x rat, 1x snake\
					return new ArrayList<>(Arrays.asList(
							Dungeon.老鼠蝙蝠?Bat.class:Rat.class,
							Dungeon.老鼠蝙蝠?Bat.class:Rat.class,
							Dungeon.老鼠蝙蝠?Bat.class:Rat.class,
							蟑螂.class));
			case 2:
					//2x rat, 1x snake, 2x gnoll
					return new ArrayList<>(Arrays.asList(Dungeon.老鼠蝙蝠?Bat.class:水蛭.class,
														 Dungeon.老鼠蝙蝠?Bat.class:水蛭.class,
														 Snake.class,
														 Snake.class, Snake.class));
				
			case 3:
				//1x rat, 1x snake, 3x gnoll, 1x swarm, 1x crab
				return new ArrayList<>(Arrays.asList(Dungeon.老鼠蝙蝠?Bat.class:水蛭.class,
						Snake.class,
						Gnoll.class, Gnoll.class, Gnoll.class,
						Swarm.class,
						Crab.class));
			case 4: case 5:
				//1x gnoll, 1x swarm, 2x crab, 2x slime
				return new ArrayList<>(Arrays.asList(Gnoll.class,
						Swarm.class,
						Crab.class, Crab.class,
						Slime.class, Slime.class));

			// Prison
			case 6:
				//3x skeleton, 1x thief, 1x swarm
				return new ArrayList<>(Arrays.asList(Skeleton.class, Skeleton.class, Skeleton.class,
						Thief.class,
						Swarm.class));
			case 7:
				//3x skeleton, 1x thief, 1x DM-100, 1x guard
				return new ArrayList<>(Arrays.asList(Skeleton.class, Skeleton.class, Skeleton.class,
						Thief.class,
						DM100.class,
						Guard.class));
			case 8:
				//2x skeleton, 1x thief, 2x DM-100, 2x guard, 1x necromancer
				return new ArrayList<>(Arrays.asList(Skeleton.class, Skeleton.class,
						Thief.class,
						DM100.class, DM100.class,
						Guard.class, Guard.class,
						Necromancer.class));
			case 9: case 10:
				//1x skeleton, 1x thief, 2x DM-100, 2x guard, 2x necromancer
				return new ArrayList<>(Arrays.asList(Skeleton.class,
						Thief.class,
						DM100.class, DM100.class,
						Guard.class, Guard.class,
						Necromancer.class, Necromancer.class));

			// Caves
			case 11:
				//3x bat, 1x brute, 1x shaman
				return new ArrayList<>(Arrays.asList(
						蠕虫.class,
						蠕虫.class,
						蠕虫.class,
						Brute.class,
						Shaman.random()));
			case 12:
				//2x bat, 2x brute, 1x shaman, 1x spinner
				return new ArrayList<>(Arrays.asList(
						石虱.class,
						石虱.class,
						Brute.class, Brute.class,
						Shaman.random(),
						Spinner.class));
			case 13:
				//1x bat, 2x brute, 2x shaman, 2x spinner, 1x DM-200
				return new ArrayList<>(Arrays.asList(
						Dungeon.老鼠蝙蝠?Rat.class:Bat.class,
						Brute.class, Brute.class,
						Shaman.random(), Shaman.random(),
						Spinner.class, Spinner.class,
						DM200.class));
			case 14: case 15:
				//1x bat, 1x brute, 2x shaman, 2x spinner, 2x DM-300
				return new ArrayList<>(Arrays.asList(
						Dungeon.老鼠蝙蝠?Rat.class:Bat.class,
						Brute.class,
						Shaman.random(), Shaman.random(),
						Spinner.class, Spinner.class,
						DM200.class, DM200.class));

			// City
			case 16:
				//3x ghoul, 1x elemental, 1x warlock
				return new ArrayList<>(Arrays.asList(
						Ghoul.class, Ghoul.class, Ghoul.class,
						Elemental.random(),
						Warlock.class));
			case 17:
				//1x ghoul, 2x elemental, 1x warlock, 1x monk
				return new ArrayList<>(Arrays.asList(
						Ghoul.class,
						Elemental.random(), Elemental.random(),
						Warlock.class,
						Monk.class));
			case 18:
				//1x ghoul, 1x elemental, 2x warlock, 2x monk, 1x golem
				return new ArrayList<>(Arrays.asList(
						Ghoul.class,
						Elemental.random(),
						Warlock.class, Warlock.class,
						Monk.class, Monk.class,
						Golem.class));
			case 19: case 20:
				//1x elemental, 2x warlock, 2x monk, 3x golem
				return new ArrayList<>(Arrays.asList(
						Elemental.random(),
						Warlock.class, Warlock.class,
						Monk.class, Monk.class,
						Golem.class, Golem.class, Golem.class));

			// Halls
			case 21:
				//2x succubus, 1x evil eye
				return new ArrayList<>(Arrays.asList(
						Succubus.class, Succubus.class,
						Eye.class));
			case 22:
				//1x succubus, 1x evil eye
				return new ArrayList<>(Arrays.asList(
						Succubus.class,
						Eye.class));
			case 23:
				//1x succubus, 2x evil eye, 1x scorpio
				return new ArrayList<>(Arrays.asList(
						地狱猎犬.class,
						Eye.class, Eye.class,
						Scorpio.class));
			case 24: case 25: case 26:
				//1x succubus, 2x evil eye, 3x scorpio
				return new ArrayList<>(Arrays.asList(
						地狱猎犬.class,
						Eye.class, Eye.class,
						Scorpio.class, Scorpio.class, Scorpio.class));
		}

	}

	//has a chance to add a rarely spawned mobs to the rotation
	public static void addRareMobs( int depth, ArrayList<Class<?extends Mob>> rotation ){
		if(Holiday.getCurrentHoliday()==Holiday.中元节){
			rotation.add(Wraith.class);
		}
		if(Dungeon.赛季(赛季设置.鬼怨地牢)){
			return;
		}
		if(Dungeon.赛季(赛季设置.刷子地牢)&&depth>25){
			depth%=25;//解析：超过25求余即是循环层
		}
		float x=0.025f;
		if(Holiday.getCurrentHoliday()==Holiday._1024){
			x*=2;
		}
		switch (depth){

			// Sewers
			default:
				return;
			case 4:
				if (Random.Float() < x) rotation.add(Thief.class);
				return;

			// Prison
			case 9:
				if (Random.Float() < x) rotation.add(Dungeon.老鼠蝙蝠?Rat.class:Bat.class);
				return;

			// Caves
			case 14:
				if (Random.Float() < x) rotation.add(Ghoul.class);
				return;

			// City
			case 19:
				if (Random.Float() < x) rotation.add(Succubus.class);
				return;
		}
	}

	//switches out regular mobs for their alt versions when appropriate
	private static void swapMobAlts(ArrayList<Class<?extends Mob>> rotation) {
		float altChance = 1 / 50f * RatSkull.exoticChanceMultiplier();
		if(Holiday.getCurrentHoliday()==Holiday._1024){
			altChance*=2;
		}
		if(Dungeon.赛季(赛季设置.鬼怨地牢)){
			altChance*=20;
		}

		if(Dungeon.hero()&&(Dungeon.hero.heroClass(HeroClass.鼠弟)||Dungeon.hero.heroClass(HeroClass.灵猫))){
			altChance*=20;
		}
		for (int i = 0; i < rotation.size(); i++) {
			if (Random.Float() < altChance) {
				Class<? extends Mob> cl = rotation.get(i);
				Class<? extends Mob> alt = RARE_ALTS.get(cl);
				if(Holiday.getCurrentHoliday()==Holiday.中元节){
					alt = Wraith.class;
				}
				if (alt != null) {
					rotation.set(i, alt);
				}
			}
		}
	}

	public static final HashMap<Class<?extends Mob>, Class<?extends Mob>> RARE_ALTS = new HashMap<>();
	static {
		RARE_ALTS.put(鬼怨.class,            仇鬼.class);
		RARE_ALTS.put(Rat.class,            Albino.class);
		RARE_ALTS.put(Gnoll.class,          GnollExile.class);
		RARE_ALTS.put(Crab.class,           HermitCrab.class);
		RARE_ALTS.put(Slime.class,          CausticSlime.class);

		RARE_ALTS.put(Thief.class,          Bandit.class);
		RARE_ALTS.put(Skeleton.class,          骷髅战士.class);
		RARE_ALTS.put(Necromancer.class,    SpectralNecromancer.class);

		RARE_ALTS.put(Brute.class,          ArmoredBrute.class);
		RARE_ALTS.put(DM200.class,          DM201.class);

		RARE_ALTS.put(Monk.class,           Senior.class);
		//swapping to chaos elemental actually happens in Elemental.random
		RARE_ALTS.put(Elemental.class,      Elemental.ChaosElemental.class);
				
		RARE_ALTS.put(Scorpio.class,        Acidic.class);
	}
}

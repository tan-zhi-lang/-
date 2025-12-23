

package com.shatteredpixel.shatteredpixeldungeon.levels.features;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Blacksmith;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Camouflage;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.food.地牢浆果;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.PetrifiedSeed;
import com.shatteredpixel.shatteredpixeldungeon.items.生命果;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.MiningLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.utils.Random;

public class HighGrass {
	
	//prevents items dropped from grass, from trampling that same grass.
	//yes this is a bit ugly, oh well.
	private static boolean freezeTrample = false;

	public static void trample( Level level, int pos ) {
		
		if (freezeTrample) return;
		
		Char ch = Actor.findChar(pos);
		
		if (level.map[pos] == Terrain.FURROWED_GRASS){
			if (ch instanceof Hero && ((Hero) ch).heroClass == HeroClass.HUNTRESS){
				//Do nothing
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}
			
		} else {
			if (ch instanceof Hero && ((Hero) ch).heroClass == HeroClass.HUNTRESS){
				Level.set(pos, Terrain.FURROWED_GRASS);
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}

			int 自然层 = 0;
			
			if (ch != null) {
				SandalsOfNature.Naturalism naturalism = ch.buff( SandalsOfNature.Naturalism.class );
				if (naturalism != null) {
					if (!naturalism.isCursed()) {
						自然层 = naturalism.itemLevel() + 1;
						naturalism.charge();
					} else {
						自然层 = -1;
					}
				}

//				//berries try to drop on floors 2/3/4/6/7/8, to a max of 4/6
//				if (ch instanceof Hero ){
//					int berriesAvailable = 2 + 2;
//
//					Talent.NatureBerriesDropped dropped = Buff.施加(ch, Talent.NatureBerriesDropped.class);
//					berriesAvailable -= dropped.count();
//
//					if (berriesAvailable > 0) {
//						int targetFloor = 2 + 2;
//						targetFloor -= berriesAvailable;
//						targetFloor += (targetFloor >= 5) ? 3 : 2;
//
//						//If we're behind: 1/10, if we're on page: 1/30, if we're ahead: 1/90
//						boolean droppingBerry = false;
//						if (Dungeon.depth > targetFloor) droppingBerry = Random.Int(10) == 0;
//						else if (Dungeon.depth == targetFloor) droppingBerry = Random.Int(30) == 0;
//						else if (Dungeon.depth < targetFloor) droppingBerry = Random.Int(90) == 0;
//
//						if (droppingBerry) {
//							dropped.countUp(1);
//							level.drop(new 地牢浆果(), pos).sprite.drop();
//						}
//					}
//
//				}
			}

			//grass gives 1/3 the normal amount of loot in fungi level
			if (Dungeon.level instanceof MiningLevel
					&& Blacksmith.Quest.Type() == Blacksmith.Quest.FUNGI
					&& Random.Int(3) != 0){
				自然层 = -1;
			}
			
			if (自然层 >= 0) {
				// Seed, scales from 1/25 to 1/9
				float 概率 = 1/(25f - 自然层 *4f);
				
//				float 概率 = 1/(9f + 自然层 *4f);
				// absolute max drop rate is ~1/6.5 with footwear of nature, ~1/18 without
				概率 *= PetrifiedSeed.grassLootMultiplier();
				if(Holiday.getCurrentHoliday()==Holiday.植树节){
					概率 *=2;
				}
				if(算法.isDebug()){
					概率=1;
				}
				if (Random.Float() < 概率) {
					if (Random.Float() < PetrifiedSeed.stoneInsteadOfSeedChance()) {
						level.drop(Generator.randomUsingDefaults(Generator.Category.STONE), pos).sprite.drop();
					} else {
						level.drop(Generator.random(Generator.Category.SEED), pos).sprite.drop();
					}
				}
				
				if (Random.Float() < 概率&&Dungeon.hero.heroClass(HeroClass.HUNTRESS)) {
					level.drop(new 地牢浆果(), pos).sprite.drop();
				}
				
				// Dew, scales from 1/6 to 1/4
				概率 = 1/(6f - 自然层 /2f);
//				概率 = 1/(4f + 自然层 /2f);
				//grassy levels spawn half as much dew
				if (Dungeon.level != null && Dungeon.level.feeling == Level.Feeling.GRASS){
					概率 /= 2;
				}
				if(算法.isDebug()){
					概率=1;
				}

				if (Random.Float() < 概率) {
					level.drop(new Dewdrop(), pos).sprite.drop();
				}
				if (Random.Float() < 1/500f&&Dungeon.LimitedDrops.生命果.count==0) {
					Dungeon.LimitedDrops.生命果.count++;
					level.drop(new 生命果(),pos).sprite.drop();
				}
				
			}

			if (ch != null) {
				Camouflage.activate(ch, ch.glyphLevel(Camouflage.class));
			}
			
		}
		
		freezeTrample = false;
		
		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			GameScene.updateMap(pos);
			
			CellEmitter.get(pos).burst(LeafParticle.LEVEL_SPECIFIC, 4);
			if (Dungeon.level.heroFOV[pos]) Dungeon.observe();
		}
	}
	public static void trample3( Level level, int pos ) {
		
		if (freezeTrample) return;
		
		Char ch = Actor.findChar(pos);
		
		if (level.map[pos] == Terrain.FURROWED_GRASS){
			if (ch instanceof Hero && ((Hero) ch).heroClass == HeroClass.HUNTRESS){
				//Do nothing
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}
			
		} else {
			if (ch instanceof Hero && ((Hero) ch).heroClass == HeroClass.HUNTRESS){
				Level.set(pos, Terrain.FURROWED_GRASS);
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}

			int 自然层 = 0;
			
			if (ch != null) {
				SandalsOfNature.Naturalism naturalism = ch.buff( SandalsOfNature.Naturalism.class );
				if (naturalism != null) {
					if (!naturalism.isCursed()) {
						自然层 = naturalism.itemLevel() + 1;
						naturalism.charge();
					} else {
						自然层 = -1;
					}
				}

//				//berries try to drop on floors 2/3/4/6/7/8, to a max of 4/6
//				if (ch instanceof Hero ){
//					int berriesAvailable = 2 + 2;
//
//					Talent.NatureBerriesDropped dropped = Buff.施加(ch, Talent.NatureBerriesDropped.class);
//					berriesAvailable -= dropped.count();
//
//					if (berriesAvailable > 0) {
//						int targetFloor = 2 + 2;
//						targetFloor -= berriesAvailable;
//						targetFloor += (targetFloor >= 5) ? 3 : 2;
//
//						//If we're behind: 1/10, if we're on page: 1/30, if we're ahead: 1/90
//						boolean droppingBerry = false;
//						if (Dungeon.depth > targetFloor) droppingBerry = Random.Int(10) == 0;
//						else if (Dungeon.depth == targetFloor) droppingBerry = Random.Int(30) == 0;
//						else if (Dungeon.depth < targetFloor) droppingBerry = Random.Int(90) == 0;
//
//						if (droppingBerry) {
//							dropped.countUp(1);
//							level.drop(new 地牢浆果(), pos).sprite.drop();
//						}
//					}
//
//				}
			}

			//grass gives 1/3 the normal amount of loot in fungi level
			if (Dungeon.level instanceof MiningLevel
					&& Blacksmith.Quest.Type() == Blacksmith.Quest.FUNGI
					&& Random.Int(3) != 0){
				自然层 = -1;
			}
			
			if (自然层 >= 0) {
				// Seed, scales from 1/25 to 1/9
				float 概率 = 1/(25f - 自然层 *4f);
				
//				float 概率 = 1/(9f + 自然层 *4f);
				// absolute max drop rate is ~1/6.5 with footwear of nature, ~1/18 without
				概率 *= PetrifiedSeed.grassLootMultiplier();
				if(Holiday.getCurrentHoliday()==Holiday.植树节){
					概率 *=2;
				}
				概率*=3;
				if(算法.isDebug()){
					概率=1;
				}
				if (Random.Float() < 概率) {
					if (Random.Float() < PetrifiedSeed.stoneInsteadOfSeedChance()) {
						level.drop(Generator.randomUsingDefaults(Generator.Category.STONE), pos).sprite.drop();
						level.drop(Generator.randomUsingDefaults(Generator.Category.STONE), pos).sprite.drop();
						level.drop(Generator.randomUsingDefaults(Generator.Category.STONE), pos).sprite.drop();
					} else {
						level.drop(Generator.random(Generator.Category.SEED), pos).sprite.drop();
						level.drop(Generator.random(Generator.Category.SEED), pos).sprite.drop();
						level.drop(Generator.random(Generator.Category.SEED), pos).sprite.drop();
					}
				}
				
				if (Random.Float() < 概率&&Dungeon.hero.heroClass(HeroClass.HUNTRESS)) {
					level.drop(new 地牢浆果(), pos).sprite.drop();
					level.drop(new 地牢浆果(), pos).sprite.drop();
					level.drop(new 地牢浆果(), pos).sprite.drop();
				}
				
				// Dew, scales from 1/6 to 1/4
				概率 = 1/(6f - 自然层 /2f);
//				概率 = 1/(4f + 自然层 /2f);
				//grassy levels spawn half as much dew
				if (Dungeon.level != null && Dungeon.level.feeling == Level.Feeling.GRASS){
					概率 /= 2;
				}
				概率*=3;
				if(算法.isDebug()){
					概率=1;
				}

				if (Random.Float() < 概率) {
					level.drop(new Dewdrop(), pos).sprite.drop();
					level.drop(new Dewdrop(), pos).sprite.drop();
					level.drop(new Dewdrop(), pos).sprite.drop();
				}
				if (Random.Float() < 1/500f&&Dungeon.LimitedDrops.生命果.count==0) {
					Dungeon.LimitedDrops.生命果.count++;
					level.drop(new 生命果(),pos).sprite.drop();
				}
				
			}

			if (ch != null) {
				Camouflage.activate(ch, ch.glyphLevel(Camouflage.class));
			}
			
		}
		
		freezeTrample = false;
		
		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			GameScene.updateMap(pos);
			
			CellEmitter.get(pos).burst(LeafParticle.LEVEL_SPECIFIC, 4);
			if (Dungeon.level.heroFOV[pos]) Dungeon.observe();
		}
	}
}

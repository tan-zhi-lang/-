

package com.shatteredpixel.shatteredpixeldungeon.levels.features;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Blacksmith;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.迷彩;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.food.红蘑菇;
import com.shatteredpixel.shatteredpixeldungeon.items.food.绿蘑菇;
import com.shatteredpixel.shatteredpixeldungeon.items.food.蓝蘑菇;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.PetrifiedSeed;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.丛林玫瑰;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.海克斯秘卷;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.生命果;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.MiningLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.VaultLevel;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sungrass;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.watabou.utils.Random;

public class HighGrass {
	
	//prevents items dropped from grass, from trampling that same grass.
	//yes this is a bit ugly, oh well.
	private static boolean freezeTrample = false;
	public static float 概率(){
		float x=1;
		if(Dungeon.hero()){
			x*=Dungeon.hero.幸运机制();
		}
		return x;
	}public static void tramplehero(Level level, int pos) {
		trampleInternal(level, pos, Dungeon.hero, false);
	}

	public static void trample(Level level, int pos) {
		trampleInternal(level, pos, Actor.findChar(pos), false);
	}

	public static void trample3(Level level, int pos) {
		trampleInternal(level, pos, Actor.findChar(pos), true);
	}

	private static void trampleInternal(Level level, int pos, Char ch, boolean 三倍) {
		if (freezeTrample) return;

		// 处理草地变化
		if (level.map[pos] == Terrain.FURROWED_GRASS) {
			if (ch instanceof Hero hero && hero.heroClass(HeroClass.HUNTRESS)) {
				freezeTrample = true;
				// 不改变地形
			} else {
				Level.set(pos, Terrain.GRASS);
			}
		} else {
			if (ch instanceof Hero hero && hero.heroClass(HeroClass.HUNTRESS)) {
				Level.set(pos, Terrain.FURROWED_GRASS);
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}

			真踩踏(level,pos,ch,三倍);
		}

		freezeTrample = false;

		// 更新场景
		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			GameScene.updateMap(pos);
			CellEmitter.get(pos).burst(LeafParticle.LEVEL_SPECIFIC, 4);
			if (Dungeon.level.heroFOV[pos]) Dungeon.observe();
		}
	}

	private static void 真踩踏(Level level,int pos,Char ch,boolean 三倍){
		int 自然层 = 0;

		// 获取自然之履 buff
		if (ch!=null) {
			SandalsOfNature.Naturalism naturalism = ch.buff(SandalsOfNature.Naturalism.class);
			if (naturalism != null) {
				自然层 = naturalism.itemLevel() + 1;
				naturalism.charge();
			}
		}

		// 特殊关卡减益
		if (Dungeon.level instanceof MiningLevel
			&& Blacksmith.Quest.Type() == Blacksmith.Quest.FUNGI
			&& Random.Int(3) != 0) {
			自然层 = -1;
		}
		if (Dungeon.level instanceof VaultLevel) {
			自然层 = -1;
		}
		if (Dungeon.符文("升级自然之履")) {
			自然层 += 2;
		}

		if (自然层 >= 0) {
			// ---- 种子掉落 ----
			float 概率 = 1 / (25f - 自然层 * 4f);
			概率 *= 概率();
			概率 *= PetrifiedSeed.grassLootMultiplier();
			if (Holiday.getCurrentHoliday() == Holiday.植树节) {
				概率 *= 2;
			}
			// 如果是 三倍，概率乘以3，并掉落3份
			if (三倍) {
				概率 *= 3;
			}
			for (int i = 0; i < (三倍? 3 : 1); i++) {
				if (Random.Float() < 概率) {
					if (Random.Float() < PetrifiedSeed.stoneInsteadOfSeedChance()) {
						level.drop(Generator.randomUsingDefaults(Generator.Category.STONE),pos).sprite().drop();
					} else {
						level.drop(Generator.random(Generator.Category.SEED),pos).sprite().drop();
					}
				}
			}

			// 蘑菇（特殊）
			for (int i = 0; i < (三倍? 3 : 1); i++){
				if(Dungeon.符文("灵感咕力咕力咕力灵感菇")||(Random.Float()<概率&&Dungeon.hero.heroClass(HeroClass.HUNTRESS))){
					if(Dungeon.白天())
						level.drop(Random.oneOf(new 红蘑菇()),pos).sprite().drop();
					else if(Dungeon.黄昏())
						level.drop(Random.oneOf(new 绿蘑菇()),pos).sprite().drop();
					else if(Dungeon.夜晚())
						level.drop(Random.oneOf(new 蓝蘑菇()),pos).sprite().drop();
				}
			}
			// ---- 露珠掉落 ----
			概率 = 1 / (6f - 自然层 / 2f);
			if (Dungeon.level != null && Dungeon.level.feeling == Level.Feeling.GRASS) {
				概率 /= 2;
			}
			if (三倍) {
				概率 *= 3;
			}
			概率 *= 概率();
			for (int i = 0; i < (三倍? 3 : 1); i++) {
				if (Random.Float() < 概率) {
					level.drop(new Dewdrop(),pos).sprite().drop();
				}
			}

			// 繁花宠爱
			if (Dungeon.符文("繁花的宠爱")) {
				level.drop(new Sungrass.Seed(),pos).sprite().drop();
			}

			// 元素掌控天赋
			if (Dungeon.hero() && Dungeon.hero.天赋(Talent.元素掌控)) {
				float 额外 = Dungeon.hero.天赋点数(Talent.元素掌控, 0.025f);
				if (三倍) 额外 *= 3;
				Dungeon.hero.生命成长 += 额外;
			}

			// 生命果
			for (int i = 0; i < (三倍? 3 : 1); i++){
				if(Random.Float()<(三倍?
										   1/100f:
										   1/300f)*概率()&&Dungeon.LimitedDrops.生命果.count<(Dungeon.符文("更多生命水晶和生命果")?
																									  20:
																									  1)){
					Dungeon.LimitedDrops.生命果.count++;
					level.drop(new 生命果(),pos).sprite().drop();
				}
			}

			// 丛林玫瑰
			for (int i = 0; i < (三倍? 3 : 1); i++){
				if(Random.Float()<(三倍?
										   1f/(24*20):
										   1f/(24*60))*概率()&&Dungeon.LimitedDrops.丛林玫瑰.count<1){
					Dungeon.LimitedDrops.丛林玫瑰.count++;
					level.drop(new 丛林玫瑰(),pos).sprite().drop();
				}
			}

			// 海克斯秘卷
			for (int i = 0; i < (三倍? 3 : 1); i++){
				if(Random.Float()<(三倍?
										   1/100f:
										   1/300f)*概率()&&Dungeon.符文("海克斯获取:收获")){
					level.drop(new 海克斯秘卷(true),pos).sprite().drop();
				}
			}
		}

		// 迷彩激活

		for (int i = 0; i < (三倍? 3 : 1); i++){
			if(ch instanceof Hero){
				迷彩.activate(ch,ch.glyphLevel(迷彩.class));
			}
		}
	}
}

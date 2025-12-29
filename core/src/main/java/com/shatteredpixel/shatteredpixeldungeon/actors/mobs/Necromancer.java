

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.NecromancerSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SkeletonSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.玩法设置;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Necromancer extends Mob {
	
	{
		spriteClass = NecromancerSprite.class;
		
		生命 = 最大生命 = 40;
		defenseSkill = 14;
		
		经验 = 7;
		最大等级 = 14;
		
		loot = 治疗药剂.class;
		lootChance = 0.2f; //see lootChance()
		
		properties.add(Property.UNDEAD);
		
		HUNTING = new Hunting();
	}
	
	public boolean summoning = false;
	public int summoningPos = -1;
	
	protected boolean firstSummon = true;
	
	private NecroSkeleton mySkeleton;
	private int storedSkeletonID = -1;

	@Override
	protected boolean act() {
		if (summoning && state != HUNTING){
			summoning = false;
			if (sprite instanceof NecromancerSprite) ((NecromancerSprite) sprite).cancelSummoning();
		}
		return super.act();
	}

	@Override
	public void aggro(Char ch) {
		super.aggro(ch);
		if (mySkeleton != null && mySkeleton.isAlive()
				&& Dungeon.level.mobs.contains(mySkeleton)
				&& mySkeleton.alignment == alignment){
			mySkeleton.aggro(ch);
		}
	}

	@Override
	public int 最大防御() {
		return super.最大防御()+5;
	}
	
	@Override
	public float lootChance() {
		return super.lootChance() * ((6f - Dungeon.LimitedDrops.NECRO_HP.count) / 6f);
	}
	
	@Override
	public Item createLoot(){
		if(!Dungeon.玩法(玩法设置.刷子地牢))
		Dungeon.LimitedDrops.NECRO_HP.count++;
		return super.createLoot();
	}
	
	@Override
	public void 死亡时(Object cause) {
		if (storedSkeletonID != -1){
			Actor ch = Actor.findById(storedSkeletonID);
			storedSkeletonID = -1;
			if (ch instanceof NecroSkeleton){
				mySkeleton = (NecroSkeleton) ch;
			}
		}
		
		if (mySkeleton != null && mySkeleton.isAlive() && mySkeleton.alignment == alignment){
			mySkeleton.死亡时(null);
		}
		
		super.死亡时(cause);
	}

	@Override
	protected boolean canAttack(Char enemy) {
		return false;
	}

	private static final String SUMMONING = "summoning";
	private static final String FIRST_SUMMON = "first_summon";
	private static final String SUMMONING_POS = "summoning_pos";
	private static final String MY_SKELETON = "my_skeleton";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put( SUMMONING, summoning );
		bundle.put( FIRST_SUMMON, firstSummon );
		if (summoning){
			bundle.put( SUMMONING_POS, summoningPos);
		}
		if (mySkeleton != null){
			bundle.put( MY_SKELETON, mySkeleton.id() );
		} else if (storedSkeletonID != -1){
			bundle.put( MY_SKELETON, storedSkeletonID );
		}
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		summoning = bundle.getBoolean( SUMMONING );
		if (bundle.contains(FIRST_SUMMON)) firstSummon = bundle.getBoolean(FIRST_SUMMON);
		if (summoning){
			summoningPos = bundle.getInt( SUMMONING_POS );
		}
		if (bundle.contains( MY_SKELETON )){
			storedSkeletonID = bundle.getInt( MY_SKELETON );
		}
	}
	
	public void onZapComplete(){
		if (mySkeleton == null || mySkeleton.sprite == null || !mySkeleton.isAlive()){
			return;
		}
		
		//heal skeleton first
		if (mySkeleton.生命 < mySkeleton.最大生命){

			if (sprite.visible || mySkeleton.sprite.visible) {
				sprite.parent.add(new Beam.HealthRay(sprite.center(), mySkeleton.sprite.center()));
			}
			
			mySkeleton.回血(mySkeleton.最大生命(0.2f));
			
		//otherwise give it adrenaline
		} else if (mySkeleton.buff(Adrenaline.class) == null) {

			if (sprite.visible || mySkeleton.sprite.visible) {
				sprite.parent.add(new Beam.HealthRay(sprite.center(), mySkeleton.sprite.center()));
			}
			
			Buff.施加(mySkeleton, Adrenaline.class, 3f);
		}
		
		next();
	}

	public void summonMinion(){
		if (Actor.findChar(summoningPos) != null) {

			int pushPos = pos;
			for (int c : PathFinder.NEIGHBOURS8) {
				if (Actor.findChar(summoningPos + c) == null
						&& Dungeon.level.passable[summoningPos + c]
						&& (Dungeon.level.openSpace[summoningPos + c] || !hasProp(Actor.findChar(summoningPos), Property.LARGE))
						&& Dungeon.level.trueDistance(pos, summoningPos + c) > Dungeon.level.trueDistance(pos, pushPos)) {
					pushPos = summoningPos + c;
				}
			}

			//no push if char is immovable
			if (Char.hasProp(Actor.findChar(summoningPos), Property.IMMOVABLE)){
				pushPos = pos;
			}

			//push enemy, or wait a turn if there is no valid pushing position
			if (pushPos != pos) {
				Char ch = Actor.findChar(summoningPos);
				Actor.add( new Pushing( ch, ch.pos, pushPos ) );

				ch.pos = pushPos;
				Dungeon.level.occupyCell(ch );

			} else {

				Char blocker = Actor.findChar(summoningPos);
				if (blocker.alignment != alignment){
					int dmg=Random.NormalIntRange(2, 10);
					
					dmg=Math.round(dmg*Dungeon.难度攻击());
					blocker.受伤时(dmg, new SummoningBlockDamage() );
					if (blocker == Dungeon.hero && !blocker.isAlive()){
						Badges.validateDeathFromEnemyMagic();
						Dungeon.fail(this);
						GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );
					}
				}

				spend(TICK);
				return;
			}
		}

		summoning = firstSummon = false;

		mySkeleton = new NecroSkeleton();
		mySkeleton.pos = summoningPos;
		GameScene.add( mySkeleton );
		Dungeon.level.occupyCell( mySkeleton );
		((NecromancerSprite)sprite).finishSummoning();

		for (Buff b : buffs()){
			if (b.revivePersists) {
				Buff.施加(mySkeleton, b.getClass());
			}
		}
	}

	public static class SummoningBlockDamage{}
	
	private class Hunting extends Mob.Hunting{
		
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			enemySeen = enemyInFOV;

			if (enemySeen){
				target = enemy.pos;
			}
			
			if (storedSkeletonID != -1){
				Actor ch = Actor.findById(storedSkeletonID);
				storedSkeletonID = -1;
				if (ch instanceof NecroSkeleton){
					mySkeleton = (NecroSkeleton) ch;
				}
			}
			
			if (summoning){
				summonMinion();
				return true;
			}
			
			if (mySkeleton != null &&
					(!mySkeleton.isAlive()
					|| !Dungeon.level.mobs.contains(mySkeleton)
					|| mySkeleton.alignment != alignment)){
				mySkeleton = null;
			}
			
			//if enemy is seen, and enemy is within range, and we have no skeleton, summon a skeleton!
			if (enemySeen && Dungeon.level.distance(pos, enemy.pos) <= 4 && mySkeleton == null){
				
				summoningPos = -1;

				//we can summon around blocking terrain, but not through it, except unlocked doors
				boolean[] passable = BArray.not(Dungeon.level.solid, null);
				BArray.or(Dungeon.level.passable, passable, passable);
				PathFinder.buildDistanceMap(pos, passable, Dungeon.level.distance(pos, enemy.pos)+3);

				for (int c : PathFinder.NEIGHBOURS8){
					if (Actor.findChar(enemy.pos+c) == null
							&& PathFinder.distance[enemy.pos+c] != Integer.MAX_VALUE
							&& Dungeon.level.passable[enemy.pos+c]
							&& (!hasProp(Necromancer.this, Property.LARGE) || Dungeon.level.openSpace[enemy.pos+c])
							&& fieldOfView[enemy.pos+c]
							&& Dungeon.level.trueDistance(pos, enemy.pos+c) < Dungeon.level.trueDistance(pos, summoningPos)){
						summoningPos = enemy.pos+c;
					}
				}
				
				if (summoningPos != -1){
					
					summoning = true;
					sprite.zap( summoningPos );

					if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[summoningPos]){
						Dungeon.hero.interrupt();
					}
					
					spend( firstSummon ? TICK : 2*TICK );
				} else {
					//wait for a turn
					spend(TICK);
				}
				
				return true;
			//otherwise, if enemy is seen, and we have a skeleton...
			} else if (enemySeen && mySkeleton != null){
				
				spend(TICK);
				
				if (!fieldOfView[mySkeleton.pos]){
					
					//if the skeleton is not next to the enemy
					//teleport them to the closest spot next to the enemy that can be seen
					if (!Dungeon.level.adjacent(mySkeleton.pos, enemy.pos)){
						int telePos = -1;
						for (int c : PathFinder.NEIGHBOURS8){
							if (Actor.findChar(enemy.pos+c) == null
									&& Dungeon.level.passable[enemy.pos+c]
									&& fieldOfView[enemy.pos+c]
									&& (Dungeon.level.openSpace[enemy.pos+c] || !Char.hasProp(mySkeleton, Property.LARGE))
									&& Dungeon.level.trueDistance(pos, enemy.pos+c) < Dungeon.level.trueDistance(pos, telePos)){
								telePos = enemy.pos+c;
							}
						}
						
						if (telePos != -1){
							
							传送卷轴.appear(mySkeleton,telePos);
							mySkeleton.teleportSpend();
							
							if (sprite != null && sprite.visible){
								sprite.zap(telePos);
								return false;
							} else {
								onZapComplete();
							}
						}
					}
					
					return true;
					
				} else {
					
					//zap skeleton
					if (mySkeleton.生命 < mySkeleton.最大生命 || mySkeleton.buff(Adrenaline.class) == null) {
						if (sprite != null && sprite.visible){
							sprite.zap(mySkeleton.pos);
							return false;
						} else {
							onZapComplete();
						}
					}
					
				}
				
				return true;
				
			//otherwise, default to regular hunting behaviour
			} else {
				return super.act(enemyInFOV, justAlerted);
			}
		}
	}
	
	public static class NecroSkeleton extends Skeleton {
		
		{
			state = WANDERING;
			
			spriteClass = NecroSkeletonSprite.class;
			
			//no loot or exp
			最大等级 = -5;
			
			//20/25 health to start
			生命 = 20;
			properties.add(Property.傀儡);
		}

		@Override
		public float spawningWeight() {
			return 0;
		}

		private void teleportSpend(){
			spend(TICK);
		}
		
		public static class NecroSkeletonSprite extends SkeletonSprite{
			
			public NecroSkeletonSprite(){
				super();
				brightness(0.75f);
			}
			
			@Override
			public void resetColor() {
				super.resetColor();
				brightness(0.75f);
			}
		}
		
	}
}

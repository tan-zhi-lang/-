

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ChampionEnemy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MindVision;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.怒气;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.潜伏;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.灵魂标记;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Feint;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.ClericSpell;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.Stasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.巫术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.忍术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.道术;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.DirectableAlly;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.刺青结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.造能结晶;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Surprise;
import com.shatteredpixel.shatteredpixeldungeon.effects.Wound;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.food.纯净粮食;
import com.shatteredpixel.shatteredpixeldungeon.items.food.蜂蜜;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.幸运之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ExoticCrystals;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.幸运硬币;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.投机之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.断骨法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.血腥生肉;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.骸骨左轮;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.飞镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blooming;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Lucky;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.日炎链刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.草剃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.蜜剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.金纹拐;
import com.shatteredpixel.shatteredpixeldungeon.items.属性碎片;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.派对设置;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public abstract class Mob extends Char {

	{
		actPriority = MOB_PRIO;
		
		alignment = Alignment.ENEMY;
	}

	public AiState SLEEPING     = new Sleeping();
	public AiState HUNTING		= new Hunting();
	public AiState WANDERING	= new Wandering();
	public AiState FLEEING		= new Fleeing();
	public AiState PASSIVE		= new Passive();
	public AiState state = SLEEPING;
	
	public Class<? extends CharSprite> spriteClass;
	
	protected int target = -1;
	
	public int defenseSkill = 0;
	
	public int 经验 = 0;
	public int 最大等级 = 经验==0?25:Hero.最大等级;
	
	protected Char enemy;
	protected int enemyID = -1; //used for save/restore
	protected boolean enemySeen;
	protected boolean alerted = false;

	protected static final float TIME_TO_WAKE_UP = 1f;

	protected boolean firstAdded = true;
	protected void onAdd(){
		if (firstAdded) {
			//modify health for ascension challenge if applicable, only on first add
			float percent = 生命 / (float) 最大生命;
			最大生命 = Math.round(最大生命 * AscensionChallenge.statModifier(this));
			生命 = Math.round(最大生命 * percent);
			firstAdded = false;
		}
	}

	private static final String STATE	= "state";
	private static final String SEEN	= "seen";
	private static final String TARGET	= "target";
	private static final String MAX_LVL	= "max_lvl";

	private static final String ENEMY_ID	= "enemy_id";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		
		super.storeInBundle( bundle );

		if (state == SLEEPING) {
			bundle.put( STATE, Sleeping.TAG );
		} else if (state == WANDERING) {
			bundle.put( STATE, Wandering.TAG );
		} else if (state == HUNTING) {
			bundle.put( STATE, Hunting.TAG );
		} else if (state == FLEEING) {
			bundle.put( STATE, Fleeing.TAG );
		} else if (state == PASSIVE) {
			bundle.put( STATE, Passive.TAG );
		}
		bundle.put( SEEN, enemySeen );
		bundle.put( TARGET, target );
		bundle.put( MAX_LVL, 最大等级);

		if (enemy != null) {
			bundle.put(ENEMY_ID, enemy.id() );
		}
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		
		super.restoreFromBundle( bundle );

		String state = bundle.getString( STATE );
		if (state.equals( Sleeping.TAG )) {
			this.state = SLEEPING;
		} else if (state.equals( Wandering.TAG )) {
			this.state = WANDERING;
		} else if (state.equals( Hunting.TAG )) {
			this.state = HUNTING;
		} else if (state.equals( Fleeing.TAG )) {
			this.state = FLEEING;
		} else if (state.equals( Passive.TAG )) {
			this.state = PASSIVE;
		}

		enemySeen = bundle.getBoolean( SEEN );

		target = bundle.getInt( TARGET );

		if (bundle.contains(MAX_LVL)) 最大等级 = bundle.getInt(MAX_LVL);

		if (bundle.contains(ENEMY_ID)) {
			enemyID = bundle.getInt(ENEMY_ID);
		}

		//no need to actually save this, must be false
		firstAdded = false;
	}

	//mobs need to remember their targets after every actor is added
	public void restoreEnemy(){
		if (enemyID != -1 && enemy == null) enemy = (Char)Actor.findById(enemyID);
	}
	
	public CharSprite sprite() {
		return Reflection.newInstance(spriteClass);
	}
	
	@Override
	protected boolean act() {
		
		super.act();
		
		boolean justAlerted = alerted;
		alerted = false;
		
		if (justAlerted){
			sprite.showAlert();
		}
		
		if (paralysed > 0) {
			enemySeen = false;
			if(this instanceof 刺青结晶){
				spend( 攻击延迟() );
			}else{
				spend( TICK );
			}
			return true;
		}

		if (buff(Terror.class) != null || buff(Dread.class) != null ){
			state = FLEEING;
		}
		
		enemy = chooseEnemy();
		
		boolean enemyInFOV = enemy != null && enemy.isAlive() && fieldOfView[enemy.pos] && enemy.invisible <= 0;

		//prevents action, but still updates enemy seen status
		if (buff(Feint.AfterImage.FeintConfusion.class) != null){
			enemySeen = enemyInFOV;
			
			if(this instanceof 刺青结晶){
				spend( 攻击延迟() );
			}else{
				spend( TICK );
			}
			return true;
		}

		boolean result = state.act( enemyInFOV, justAlerted );

		//for updating hero FOV
		if (buff(PowerOfMany.PowerBuff.class) != null){
			Dungeon.level.updateFieldOfView( this, fieldOfView );
			GameScene.updateFog(pos, viewDistance+(int)Math.ceil(移速()));
		}

		return result;
	}
	
	//FIXME this is sort of a band-aid correction for allies needing more intelligent behaviour
	protected boolean intelligentAlly = false;
	public void target(int target){
		this.target=target;
	}
	public void enemy(Char enemy){
		this.enemy=enemy;
	}
	public Char chooseEnemy() {

		Dread dread = buff( Dread.class );
		if (dread != null) {
			Char source = (Char)Actor.findById( dread.object );
			if (source != null) {
				return source;
			}
		}

		Terror terror = buff( Terror.class );
		if (terror != null) {
			Char source = (Char)Actor.findById( terror.object );
			if (source != null) {
				return source;
			}
		}
		
		//if we are an alert enemy, auto-hunt a target that is affected by aggression, even another enemy
		if ((alignment == Alignment.ENEMY || buff(Amok.class) != null ) && state != PASSIVE && state != SLEEPING) {
			if (enemy != null && enemy.buff(StoneOfAggression.Aggression.class) != null){
				state = HUNTING;
				return enemy;
			}
			for (Char ch : Actor.chars()) {
				if (ch != this && fieldOfView[ch.pos] &&
						ch.buff(StoneOfAggression.Aggression.class) != null) {
					state = HUNTING;
					return ch;
				}
			}
		}

		//find a new enemy if..
		boolean newEnemy = false;
		//we have no enemy, or the current one is dead/missing
		if ( enemy == null || !enemy.isAlive() || !Actor.chars().contains(enemy) || state == WANDERING) {
			newEnemy = true;
		//We are amoked and current enemy is the hero
		} else if (buff( Amok.class ) != null && enemy == Dungeon.hero) {
			newEnemy = true;
		//We are charmed and current enemy is what charmed us
		} else if (buff(Charm.class) != null && buff(Charm.class).object == enemy.id()) {
			newEnemy = true;
		}

		//additionally, if we are an ally, find a new enemy if...
		if (!newEnemy && alignment == Alignment.ALLY){
			//current enemy is also an ally
			if (enemy.alignment == Alignment.ALLY){
				newEnemy = true;
			//current enemy is invulnerable
			} else if (enemy.是无敌(getClass())){
				newEnemy = true;
			}
		}

		if ( newEnemy ) {

			HashSet<Char> enemies = new HashSet<>();

			//if we are amoked...
			if ( buff(Amok.class) != null) {
				//try to find an enemy mob to attack first.
				for (Mob mob : Dungeon.level.mobs)
					if (mob.alignment == Alignment.ENEMY && mob != this
							&& fieldOfView[mob.pos] && mob.invisible <= 0) {
						enemies.add(mob);
					}
				
				if (enemies.isEmpty()) {
					//try to find ally mobs to attack second.
					for (Mob mob : Dungeon.level.mobs)
						if (mob.alignment == Alignment.ALLY && mob != this
								&& fieldOfView[mob.pos] && mob.invisible <= 0) {
							enemies.add(mob);
						}
					
					if (enemies.isEmpty()) {
						//try to find the hero third
						if (fieldOfView[Dungeon.hero.pos] && Dungeon.hero.invisible <= 0) {
							enemies.add(Dungeon.hero);
						}
					}
				}
				
			//if we are an ally...
			} else if ( alignment == Alignment.ALLY ) {
				//look for hostile mobs to attack
				for (Mob mob : Dungeon.level.mobs)
					if (mob.alignment == Alignment.ENEMY && fieldOfView[mob.pos]
							&& mob.invisible <= 0 && !mob.是无敌(getClass()))
						//do not target passive mobs
						//intelligent allies also don't target mobs which are wandering or asleep
						if (mob.state != mob.PASSIVE &&
								(!intelligentAlly || (mob.state != mob.SLEEPING && mob.state != mob.WANDERING))) {
							enemies.add(mob);
						}
				
			//if we are an enemy...
			} else if (alignment == Alignment.ENEMY) {
				//look for ally mobs to attack
				for (Mob mob : Dungeon.level.mobs)
					if (mob.alignment == Alignment.ALLY && fieldOfView[mob.pos] && mob.invisible <= 0)
						enemies.add(mob);

				//and look for the hero
				if (fieldOfView[Dungeon.hero.pos] && Dungeon.hero.invisible <= 0) {
					enemies.add(Dungeon.hero);
				}
				
			}

			//do not target anything that's charming us
			Charm charm = buff( Charm.class );
			if (charm != null){
				Char source = (Char)Actor.findById( charm.object );
				if (source != null && enemies.contains(source) && enemies.size() > 1){
					enemies.remove(source);
				}
			}

			//neutral characters in particular do not choose enemies.
			if (enemies.isEmpty()){
				return null;
			} else {
				//go after the closest potential enemy, preferring enemies that can be reached/attacked, and the hero if two are equidistant
				PathFinder.buildDistanceMap(pos, Dungeon.findPassable(this, Dungeon.level.passable, fieldOfView, true));
				Char closest = null;
				int closestDist = Integer.MAX_VALUE;

				for (Char curr : enemies){
					int currDist = Integer.MAX_VALUE;
					//we aren't trying to move into the target, just toward them
					for (int i : PathFinder.NEIGHBOURS8){
						if (PathFinder.distance[curr.pos+i] < currDist){
							currDist = PathFinder.distance[curr.pos+i];
						}
					}
					if (closest == null){
						closest = curr;
						closestDist = currDist;
					} else if (canAttack(closest) && !canAttack(curr)){
						continue;
					} else if ((canAttack(curr) && !canAttack(closest))
							|| (currDist < closestDist)){
						closest = curr;
					} else if ( curr == Dungeon.hero &&
							(currDist == closestDist) || (canAttack(curr) && canAttack(closest))){
						closest = curr;
					}
				}
				//if we were going to target the hero, but an afterimage exists, target that instead
				if (closest == Dungeon.hero){
					for (Char ch : enemies){
						if (ch instanceof Feint.AfterImage){
							closest = ch;
							break;
						}
					}
				}

				return closest;
			}

		} else
			return enemy;
	}
	
	@Override
	public boolean add( Buff buff ) {
		if (super.add( buff )) {
			if (buff instanceof Amok || buff instanceof AllyBuff) {
				state = HUNTING;
			} else if (buff instanceof Terror || buff instanceof Dread) {
				state = FLEEING;
			} else if (buff instanceof Sleep) {
				state = SLEEPING;
				postpone(Sleep.SWS);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove( Buff buff ) {
		if (super.remove( buff )) {
			if (state == FLEEING && ((buff instanceof Terror && buff(Dread.class) == null)
					|| (buff instanceof Dread && buff(Terror.class) == null))) {
				if (enemySeen) {
					sprite.showStatus(CharSprite.WARNING, Messages.get(this, "rage"));
					state = HUNTING;
				} else {
					state = WANDERING;
				}
			}
			return true;
		}
		return false;
	}
	
	protected boolean canAttack( Char enemy ) {
		if (Dungeon.level.adjacent( pos, enemy.pos )){
			return true;
		}
		for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
			if (buff.canAttackWithExtraReach( enemy )){
				return true;
			}
		}
		return false;
	}

	private boolean cellIsPathable( int cell ){
		if (!Dungeon.level.passable[cell]){
			if (flying || buff(Amok.class) != null){
				if (!Dungeon.level.avoid[cell]){
					return false;
				}
			} else {
				return false;
			}
		}
		if (Char.hasProp(this, Char.Property.LARGE) && !Dungeon.level.openSpace[cell]){
			return false;
		}
		if (Actor.findChar(cell) != null){
			return false;
		}

		return true;
	}

	protected boolean getCloser( int target ) {
		
		if (rooted || target == pos) {
			return false;
		}

		int step = -1;

		if (Dungeon.level.adjacent( pos, target )) {

			path = null;

			if (cellIsPathable(target)) {
				step = target;
			}

		} else {

			boolean newPath = false;
			float longFactor = state == WANDERING ? 2f : 1.33f;
			//scrap the current path if it's empty, no longer connects to the current location
			//or if it's quite inefficient and checking again may result in a much better path
			//mobs are much more tolerant of inefficient paths if wandering
			if (path == null || path.isEmpty()
					|| !Dungeon.level.adjacent(pos, path.getFirst())
					|| path.size() > longFactor*Dungeon.level.distance(pos, target))
				newPath = true;
			else if (path.getLast() != target) {
				//if the new target is adjacent to the end of the path, adjust for that
				//rather than scrapping the whole path.
				if (Dungeon.level.adjacent(target, path.getLast())) {
					int last = path.removeLast();

					if (path.isEmpty()) {

						//shorten for a closer one
						if (Dungeon.level.adjacent(target, pos)) {
							path.add(target);
						//extend the path for a further target
						} else {
							path.add(last);
							path.add(target);
						}

					} else {
						//if the new target is simply 1 earlier in the path shorten the path
						if (path.getLast() == target) {

						//if the new target is closer/same, need to modify end of path
						} else if (Dungeon.level.adjacent(target, path.getLast())) {
							path.add(target);

						//if the new target is further away, need to extend the path
						} else {
							path.add(last);
							path.add(target);
						}
					}

				} else {
					newPath = true;
				}

			}

			//checks if the next cell along the current path can be stepped into
			if (!newPath) {
				int nextCell = path.removeFirst();
				if (!cellIsPathable(nextCell)) {

					newPath = true;
					//If the next cell on the path can't be moved into, see if there is another cell that could replace it
					if (!path.isEmpty()) {
						for (int i : PathFinder.NEIGHBOURS8) {
							if (Dungeon.level.adjacent(pos, nextCell + i) && Dungeon.level.adjacent(nextCell + i, path.getFirst())) {
								if (cellIsPathable(nextCell+i)){
									path.addFirst(nextCell+i);
									newPath = false;
									break;
								}
							}
						}
					}
				} else {
					path.addFirst(nextCell);
				}
			}

			//generate a new path
			if (newPath) {
				//If we aren't hunting, always take a full path
				PathFinder.Path full = Dungeon.findPath(this, target, Dungeon.level.passable, fieldOfView, true);
				if (state != HUNTING){
					path = full;
				} else {
					//otherwise, check if other characters are forcing us to take a very slow route
					// and don't try to go around them yet in response, basically assume their blockage is temporary
					PathFinder.Path ignoreChars = Dungeon.findPath(this, target, Dungeon.level.passable, fieldOfView, false);
					if (ignoreChars != null && (full == null || full.size() > 2*ignoreChars.size())){
						//check if first cell of shorter path is valid. If it is, use new shorter path. Otherwise do nothing and wait.
						path = ignoreChars;
						if (!cellIsPathable(ignoreChars.getFirst())) {
							return false;
						}
					} else {
						path = full;
					}
				}
			}

			if (path != null) {
				step = path.removeFirst();
			} else {
				return false;
			}
		}
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean getFurther( int target ) {
		if (rooted || target == pos) {
			return false;
		}
		
		int step = Dungeon.flee( this, target, Dungeon.level.passable, fieldOfView, true );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateSpriteState() {
		super.updateSpriteState();
		if (Dungeon.hero.buff(时光沙漏.timeFreeze.class) != null
				|| Dungeon.hero.buff(Swiftthistle.TimeBubble.class) != null)
			sprite.add( CharSprite.State.PARALYSED );
	}
	@Override
	public float 攻击延迟() {
		float delay = super.攻击延迟();

		if ( buff(Adrenaline.class) != null) delay /= 1.5f;
		return delay;
	}
	
	protected boolean doAttack( Char enemy ) {
		
		if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
			sprite.attack( enemy.pos );
			return false;
			
		} else {
			attack( enemy );
			Invisibility.dispel(this);
			spend(攻击延迟());
			return true;
		}
	}
	
	@Override
	public void onAttackComplete() {
		attack( enemy );
		Invisibility.dispel(this);
		spend(攻击延迟());
		super.onAttackComplete();
	}
	
	@Override
	public int 最大闪避(Char enemy ) {

		if ( !surprisedBy(enemy)
				&& paralysed == 0
				&& !(alignment == Alignment.ALLY && enemy == Dungeon.hero)) {
			return this.defenseSkill;
		} else {
			return 0;
		}
	}
	
	@Override
	public float 防御时(Char enemy, float damage ) {
		if(enemy instanceof Hero&&!enemySeen){//防止惊醒距离被打不惊醒
			enemySeen = true;
			notice();
			state = HUNTING;
			target = enemy.pos;
		}
		if (enemy instanceof Hero
				&& ((Hero) enemy).belongings.attackingWeapon() instanceof Weapon){
			Statistics.thrownAttacks++;
			Badges.validateHuntressUnlock();
		}
		
		if (surprisedBy(enemy)) {
			Statistics.sneakAttacks++;
			Badges.validateRogueUnlock();
			//TODO this is somewhat messy, it would be nicer to not have to manually handle delays here
			// playing the strong hit sound might work best as another property of weapon?
			if (Dungeon.hero.belongings.attackingWeapon() instanceof 灵能短弓.SpiritArrow
				|| Dungeon.hero.belongings.attackingWeapon() instanceof 飞镖){
				Sample.INSTANCE.playDelayed(Assets.Sounds.HIT_STRONG, 0.125f);
			} else {
				Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
			}
			if (enemy.buff(潜伏.class)!=null) {
				Wound.hit(this);
			} else {
				Surprise.hit(this);
			}
		}

		//if attacked by something else than current target, and that thing is closer, switch targets
		//or if attacked by target, simply update target position
		if (state != FLEEING) {
			if (state != HUNTING) {
				aggro(enemy);
				target = enemy.pos;
			} else {
				recentlyAttackedBy.add(enemy);
			}
		}

		if (buff(灵魂标记.class)!=null) {
			if (enemy != Dungeon.hero){
				Dungeon.hero.回血(damage*Dungeon.hero.天赋点数(Talent.SOUL_EATER,0.13f)*Dungeon.hero.天赋点数(Talent.SOUL_SIPHON,0.13f));
				Buff.施加(Dungeon.hero, Hunger.class).吃饭(damage*0.15f*Dungeon.hero.天赋点数(Talent.SOUL_SIPHON,0.13f));
			}else {
				Dungeon.hero.回血(damage*Dungeon.hero.天赋点数(Talent.SOUL_EATER,0.13f));
				Buff.施加(Dungeon.hero, Hunger.class).吃饭(damage*0.15f);
			}
		}

		if(Dungeon.派对(派对设置.怪物猎场)&&Math.round(damage/10/3)>0)
			Dungeon.level.drop(new 属性碎片().数量(Math.round(damage/10/3)),pos).sprite.drop();

		return super.防御时(enemy, damage);
	}

	@Override
	public float 移速() {
		return super.移速() * AscensionChallenge.enemySpeedModifier(this);
	}

	public final boolean surprisedBy( Char enemy ){
		return surprisedBy( enemy, true);
	}

	public boolean surprisedBy( Char enemy, boolean attacking ){
		return enemy == Dungeon.hero
				&& (enemy.invisible > 0 || !enemySeen || (fieldOfView != null && fieldOfView.length == Dungeon.level.length() && !fieldOfView[enemy.pos]))
				&& (!attacking || enemy.canSurpriseAttack());
	}

	//whether the hero should interact with the mob (true) or attack it (false)
	public boolean heroShouldInteract(){
		return alignment != Alignment.ENEMY && buff(Amok.class) == null;
	}

	public void aggro( Char ch ) {
		enemy = ch;
		if (state != PASSIVE){
			state = HUNTING;
		}
	}

	public void clearEnemy(){
		enemy = null;
		enemySeen = false;
		if (state == HUNTING) state = WANDERING;
	}
	
	public boolean isTargeting( Char ch){
		return enemy == ch;
	}

	@Override
	public float 吸血(){
		return super.吸血();
	}

	@Override
	public void 受伤时(float dmg, Object src ) {
		if (!是无敌(src.getClass())) {
			if (state == SLEEPING) {
				state = WANDERING;
			}
			
			if (src == Dungeon.hero
				|| src instanceof Weapon || src instanceof Weapon.Enchantment||src instanceof Wand
				||src instanceof ClericSpell||src instanceof 巫术||src instanceof 道术||src instanceof 忍术||src instanceof ArmorAbility){
				
				if(Dungeon.hero()){
					if(Dungeon.hero.全能吸血()>0){

						if(Dungeon.hero()&&Dungeon.hero.符文("猛攻")){

						}else
							Dungeon.hero.回血(dmg*Dungeon.hero.全能吸血()*((老鬼()||小老鬼())?
																			   1:
																			   1/3f));
					}
					if(Dungeon.hero.天赋(Talent.错失良机))
						Buff.延长( Dungeon.hero, Invisibility.class, Dungeon.hero.天赋点数(Talent.错失良机,2) );
					
				}
			}
			if (!(src instanceof Corruption) && state != FLEEING) {
				if (state != HUNTING) {
					alerted = true;
					//assume the hero is hitting us in these common cases
					if (src instanceof Wand||src instanceof ClericSpell||src instanceof 巫术||src instanceof 道术||src instanceof 忍术||src instanceof ArmorAbility) {
						aggro(Dungeon.hero);
						target = Dungeon.hero.pos;
					}
				} else {
					if (src instanceof Wand||src instanceof ClericSpell||src instanceof 巫术||src instanceof 道术||src instanceof 忍术||src instanceof ArmorAbility) {
						recentlyAttackedBy.add(Dungeon.hero);
					}
				}
			}
		}
		
		super.受伤时( dmg, src );
	}
	
	
	@Override
	public void destroy() {
		
		super.destroy();
		
		Dungeon.level.mobs.remove( this );

		if (Dungeon.hero.buff(MindVision.class) != null){
			Dungeon.observe();
			GameScene.updateFog(pos, 2);
		}

		if (Dungeon.hero.isAlive()) {
			
			if (alignment == Alignment.ENEMY) {
				Statistics.enemiesSlain++;
				Badges.validateMonstersSlain();
				Statistics.qualifiedForNoKilling = false;
				Bestiary.setSeen(getClass());
				Bestiary.countEncounter(getClass());

				AscensionChallenge.processEnemyKill(this);
				
				int exp =0;
				if(Dungeon.赛季(赛季设置.刷子地牢)){
					exp=经验;
				}else {
					exp=Dungeon.hero.等级 <= 最大等级 ? 经验 : 0;
				}
				
				//during ascent, under-levelled enemies grant 10 xp each until level 30
				// after this enemy kills which reduce the amulet curse still grant 10 effective xp
				// for the purposes of on-exp effects, see AscensionChallenge.processEnemyKill
				if (Dungeon.hero.buff(AscensionChallenge.class) != null &&
						exp == 0 && 最大等级 > 0 && 经验 > 0 && Dungeon.hero.等级 < Hero.最大等级){
					exp = Math.round(10 * spawningWeight());
				}

				if(!Dungeon.赛季(赛季设置.地牢塔防)){
					Dungeon.hero.经验(exp,getClass());
				}

				if (Dungeon.hero.subClass == HeroSubClass.武者){
					Buff.施加(Dungeon.hero, MonkEnergy.class).gainEnergy(this);
				}
			}
		}
	}
	
	@Override
	public void 死亡时(Object cause ) {
		if (cause == Chasm.class){
			//50% chance to round up, 50% to round down
			if (经验 % 2 == 1) 经验 += Random.Int(2);
			经验 /= 2;
		}

		if (alignment == Alignment.ENEMY){
			if (buff(Trap.HazardAssistTracker.class) != null){
				Statistics.hazardAssistedKills++;
				Badges.validateHazardAssists();
			}
			
			if(Dungeon.赛季(赛季设置.地牢塔防)){
				int 能量=1;
				if(Dungeon.hero.地牢塔防更多更快开关&&Random.Int(4)!=0)能量=0;
				if(Dungeon.hero.地牢塔防波次%3==0)能量+=最大生命(0.01f);
				
				if(Dungeon.赛季(赛季设置.地牢塔防))
					for(int n: PathFinder.范围6){
						Char c=Actor.findChar(pos+n);
						if(c instanceof 造能结晶 x&&Dungeon.level.distance(pos,x.pos)<=x.viewDistance&&Dungeon.level.heroFOV[c.pos]){
							能量=Math.round(能量*(0.3f+0.2f*x.tier));
						}
					}
				Dungeon.energy(能量);
			}else{
				rollToDropLoot();
			}
			

			if (cause == Dungeon.hero||cause instanceof Ring||cause instanceof Artifact||cause instanceof Wand||cause instanceof Weapon||cause instanceof Weapon.Enchantment){

				if(防刷()){
				if(cause== Dungeon.hero&&Dungeon.hero.belongings.attackingWeapon()!=null){
						if(Dungeon.hero.belongings.attackingWeapon()instanceof 草剃){
							for(int n: PathFinder.NEIGHBOURS8){
								Blooming.plantGrass(pos+n);
							}
						}
						if(Dungeon.hero.belongings.attackingWeapon() instanceof 日炎链刃){
							new Bomb.ConjuredBomb().heroexplode(pos);
						}
						if(Dungeon.hero.belongings.attackingWeapon() instanceof 金纹拐){
							Dungeon.level.drop(new Gold().random(),pos).sprite().drop();
						}
						if(Dungeon.hero.belongings.attackingWeapon() instanceof 蜜剑){
							Dungeon.level.drop(new 蜂蜜(),pos).sprite().drop();
						}
				}
				if(!isAlive()){
					if (Dungeon.hero.subClass(HeroSubClass.狂战士)) {
						算法.修复效果(()->{
							Buff.施加(this,怒气.class).damage();
						});
					}

					if(Dungeon.解压(解压设置.幸运女神)&&算法.概率学(经验+最大等级/2)){
						Dungeon.level.drop(new 升级卷轴(),pos).sprite().drop();
					}
					if(血腥生肉.增加()>0&&Random.Int(血腥生肉.增加()-1)==0){
						Dungeon.level.drop(new MysteryMeat(),pos).sprite().drop();
					}

					if(恶魔亡灵()&&Dungeon.hero.heroClass(HeroClass.道士)){
						Dungeon.hero.回血(Dungeon.hero.最大生命(0.03f));
						if(算法.概率学(1/8f*(1+Dungeon.hero.天赋点数(Talent.残魂侵蚀,2))))
							Dungeon.level.drop(new 纯净粮食(),pos).sprite().drop();
					}
					if(海妖()&&Dungeon.hero.天赋(Talent.捕鱼达人))
						Dungeon.level.drop(new MysteryMeat().数量(Dungeon.hero.天赋点数(Talent.捕鱼达人,2)),pos).sprite().drop();

					if((this instanceof Rat||this instanceof TransmogRat)&&Dungeon.hero.subClass(HeroSubClass.巫咒王鼠)){
						if(Random.Int(1)==0||Dungeon.hero.职业精通())
						Dungeon.level.drop(new MysteryMeat(),pos).sprite().drop();
					}

					if(Dungeon.hero.天赋概率(Talent.盗墓大师,25))
						Dungeon.level.drop(Generator.random(), pos ).type = Heap.Type.TOMB;

				if(恶魔亡灵()&&Dungeon.hero.天赋(Talent.渡魂灵猫))
					Dungeon.hero.回血(Dungeon.hero.最大生命(Dungeon.hero.天赋点数(Talent.渡魂灵猫,0.025f)));
				
				if(Dungeon.hero.heroClass(HeroClass.镜魔)&&Dungeon.hero.buff(Talent.LethalMomentumTracker.class)==null){
					Buff.施加(Dungeon.hero,Talent.LethalMomentumTracker.class,0f);
				}
				if(Dungeon.hero.heroClass(HeroClass.WARRIOR)){
					Dungeon.hero.护甲(1);
				}
				if(Dungeon.hero.heroClass(HeroClass.近卫)){
					Dungeon.hero.回已损失血(0.05f);
				}
				if(骸骨左轮.增加()>0){
					Dungeon.hero.回血(骸骨左轮.增加());
				}
				if(断骨法杖.增加()>0){
					Dungeon.hero.belongings.charge(断骨法杖.增加());
					充能卷轴.charge(Dungeon.hero);
					Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );
				}
				if(Dungeon.hero.subClass(HeroSubClass.灵魂武者))
					Dungeon.hero.力量+=0.1f+(Dungeon.hero.职业精通()?0.1f:0);
				if(投机之剑.增加()>0&&Dungeon.hero.投机之剑>0){
					Dungeon.gold(Dungeon.hero.投机之剑);
				}

				if(Dungeon.hero.符文("裁决使")){
					if(cause instanceof Wand w)
					w.gainCharge(1);
					if(cause instanceof Weapon w&&w.charger!=null)
					w.charger.gainCharge(1);
				}

				if(Dungeon.hero.符文("超凡邪恶"))Dungeon.hero.智力+=0.015f;
				if(Dungeon.hero.天赋(Talent.久战))
					Dungeon.hero.回血(Dungeon.hero.天赋点数(Talent.久战));
					//击杀瞬移
//					Buff.施加(Dungeon.hero, GreaterHaste.class).set(Dungeon.hero.天赋点数(Talent.LETHAL_HASTE));
				}
			}
		}

		}


		boolean soulMarked =buff(灵魂标记.class)!=null;

		if (!(this instanceof Wraith)
			&& soulMarked
			&& 防刷()
			&& Random.Float() < (Dungeon.hero.天赋点数(Talent.NECROMANCERS_MINIONS,0.13f))){
			Wraith w = Wraith.spawnAt(pos, Wraith.class);
			if (w != null) {
				Buff.施加(w, Corruption.class);
				if (Dungeon.level.heroFOV[pos]) {
					CellEmitter.get(pos).burst(ShadowParticle.CURSE, 6);
					Sample.INSTANCE.play(Assets.Sounds.CURSED);
				}
			}
		}

		if (Dungeon.hero.isAlive() && !Dungeon.level.heroFOV[pos]) {
			GLog.i( Messages.get(this, "died") );
		}

		if (防刷()&& 算法.概率学(1/5f)&& Dungeon.hero.符文("破败之王")){
				回满血();
				Buff.施加(this, Corruption.class);
		}else{
			if(Dungeon.hero.符文("击杀击杀")&&防刷()){
				首次死亡=true;
				死亡时( cause );
			}else
			super.死亡时( cause );
		}

	}

	public float lootChance(){
		float lootChance = this.lootChance;
		
		if(Dungeon.解压(解压设置.掉落几率)){
			lootChance*=3;
		}
		if(Dungeon.hero()&&Dungeon.hero.欧皇())lootChance*=2;
		if(Dungeon.hero()&&Dungeon.hero.非酋())lootChance/=2;
		float dropBonus = 幸运之戒.dropChanceMultiplier(Dungeon.hero);

		Talent.BountyHunterTracker bhTracker = Dungeon.hero.buff(Talent.BountyHunterTracker.class);
		if (bhTracker != null){
			潜伏 prep = Dungeon.hero.buff(潜伏.class);
			if (prep != null){
				// 2/4/8/16% per prep level, multiplied by talent points
				float bhBonus = Dungeon.hero.天赋点数(Talent.BOUNTY_HUNTER,0.1f)*prep.attackLevel();
				dropBonus += bhBonus;
			}
		}

		dropBonus += ShardOfOblivion.lootChanceMultiplier()-1f;

		return lootChance * dropBonus;
	}
	
	public void rollToDropLoot(){
		if(Dungeon.赛季(赛季设置.刷子地牢)){
		
		}else{
			if(Dungeon.hero.等级>最大等级+2)
				return;
		}
		if(防刷()){
		if(Dungeon.赛季(赛季设置.刷子地牢)&&算法.概率学(1/8f*Dungeon.难度掉率())){
			Dungeon.level.drop(Generator.random(), pos).sprite().drop();
		}
		if(Dungeon.赛季(赛季设置.刷子地牢)&&算法.概率学(1/12f*Dungeon.难度掉率())){
			Dungeon.level.drop(new 治疗药剂(),pos).sprite().drop();
		}
		MasterThievesArmband.StolenTracker stolen = buff(MasterThievesArmband.StolenTracker.class);
		if (stolen == null || !stolen.itemWasStolen()) {
			Item loot = createLoot();
			float 几率=1*Dungeon.难度掉率();
			if (loot != null) {
				if(loot.可堆叠){
					loot.数量(loot.数量()+幸运硬币.增加());
					几率*=幸运硬币.减少();
				}
				if (Random.Float() < lootChance()*几率) {
					Dungeon.level.drop(loot, pos).sprite().drop();
				}
			}
		}
		//ring of wealth logic
		if (Ring.getBuffedBonus(Dungeon.hero, 幸运之戒.Wealth.class)>0) {
			int rolls = 1;
			if (properties().contains(Property.BOSS)) rolls = 15;
			else if (properties().contains(Property.MINIBOSS)) rolls = 5;
			ArrayList<Item> bonus = 幸运之戒.tryForBonusDrop(Dungeon.hero,rolls);
			if (bonus != null && !bonus.isEmpty()) {
				for (Item b : bonus) Dungeon.level.drop(b, pos).sprite().drop();
				幸运之戒.showFlareForBonusDrop(sprite);
			}
		}
		
		//lucky enchant logic
		if (buff(Lucky.LuckProc.class) != null){
			Dungeon.level.drop(buff(Lucky.LuckProc.class).genLoot(), pos).sprite().drop();
			Lucky.showFlare(sprite);
		}
		}
	}
	
	protected Object loot = null;
	protected float lootChance = 1;//0
	
	@SuppressWarnings("unchecked")
	public Item createLoot() {
		Item item;
		if (loot instanceof Generator.Category) {

			item = Generator.randomUsingDefaults( (Generator.Category)loot );

		} else if (loot instanceof Class<?>) {

			if (ExoticPotion.regToExo.containsKey(loot)){
				if (Random.Float() < ExoticCrystals.consumableExoticChance()){
					return Generator.random(ExoticPotion.regToExo.get(loot));
				}
			} else if (ExoticScroll.regToExo.containsKey(loot)){
				if (Random.Float() < ExoticCrystals.consumableExoticChance()){
					return Generator.random(ExoticScroll.regToExo.get(loot));
				}
			}

			item = Generator.random( (Class<? extends Item>)loot );

		} else {

			item = (Item)loot;

		}
		return item;
	}

	//how many mobs this one should count as when determining spawning totals
	public float spawningWeight(){
		return 1;
	}
	
	public boolean reset() {
		return false;
	}
	
	public void beckon( int cell ) {
		
		notice();
		
		if (state != HUNTING && state != FLEEING) {
			state = WANDERING;
		}
		target = cell;
	}
	
	public String description() {
		return Messages.get(this, "desc");
	}

	public String info(){
		String desc = description();

		for (Buff b : buffs(ChampionEnemy.class)){
			desc += "\n\n_" + Messages.titleCase(b.name()) + "_\n" + b.desc();
		}
		if(!(this instanceof NPC||(this instanceof Mimic m&&
				m.alignment == Char.Alignment.ENEMY && m.state!=PASSIVE))){
			desc+="\n\n";
			
			String 属性="";
			if(this instanceof Mob&&!无机物()&&!闪电()&&!恶魔亡灵()&&!火焰()&&!寒冰()) 属性+=" 生物";
			
			if(老鬼())属性+=" 老鬼";
			if(小老鬼())属性+=" 小老鬼";
			if(老鬼傀儡())属性+=" 老鬼傀儡";
			if(傀儡())属性+=" 傀儡";
			if(低活动度生物())属性+=" 低活动度生物";
			
			if(恶魔())属性+=" 恶魔";
			if(亡灵())属性+=" 亡灵";
			
			if(庞大())属性+=" 庞大";
			if(无机物()) 属性+=" 无机物";
			if(海妖())属性+=" 海妖";
			if(植物())属性+=" 植物";
			if(昆虫())属性+=" 昆虫";
			if(动物())属性+=" 动物";
			if(酸性())属性+=" 酸性";
			if(寒冰())属性+=" 寒冰";
			if(火焰())属性+=" 火焰";
			if(闪电()) 属性+=" 闪电";
			if(静物())属性+=" 静物";
			
			desc+="属性"+属性+"\n";

			desc+="==攻击=="+String.format("%.2f",最小攻击()*Dungeon.难度攻击())+"~"
				  +String.format("%.2f",最大攻击()*Dungeon.难度攻击())+"\n";
			desc+="++防御++"+String.format("%.2f",最小防御()*Dungeon.难度防御())+"~"
				  +Math.round(最大防御()*Dungeon.难度防御())+"\n";
			desc+="命中/闪避"+String.format("%.2f",最小命中(null)*Dungeon.难度命中闪避())+"~"+
				  Math.round(最大命中(null)*Dungeon.难度命中闪避())+"/"+Math.round(最小闪避(null)*Dungeon.难度命中闪避())+"~"+
				  Math.round(最大闪避(null)*Dungeon.难度命中闪避())+"\n";
			desc+="攻速/移速"+String.format("%.2f",1/攻击延迟())+"/"+String.format("%.2f",移速())+"\n";
			desc+="经验/最大等级经验"+Math.round(经验*Dungeon.难度经验())+"/"+(最大等级+2)+"\n";
			desc+="$$暴击率/暴击伤害$$"+暴击率()+"/"+暴击伤害()*100+"%\n";
			desc+="$$视野范围$$"+viewDistance;
			desc+="掉落几率"+String.format("%.2f",lootChance()*100)+"%";
		}
		return desc;
	}
	public void notice() {
		sprite.showAlert();
	}
	
	public void yell( String str ) {
		GLog.newLine();
		sprite.说(str);//说话修改
		GLog.n( "%s: \"%s\" ", Messages.titleCase(name()), str );
	}

	//some mobs have an associated landmark entry, which is added when the hero sees them
	//mobs may also remove this landmark in some cases, such as when a quest is complete or they die
	public Notes.Landmark landmark(){
		return null;
	}

	public interface AiState {
		boolean act( boolean enemyInFOV, boolean justAlerted );
	}

	protected class Sleeping implements AiState {

		public static final String TAG	= "SLEEPING";

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {

			//debuffs cause mobs to wake as well
			for (Buff b : buffs()){
				if (b.type == Buff.buffType.NEGATIVE){
					awaken(enemyInFOV);
					if (state == SLEEPING){
						spend(TICK); //wait if we can't wake up for some reason
					}
					return true;
				}
			}

			//can be awoken by the least stealthy hostile present, not necessarily just our target
			if (enemyInFOV || (enemy != null && enemy.invisible > 0)) {

				float closestHostileDist = Float.POSITIVE_INFINITY;

				for (Char ch : Actor.chars()){
					if (fieldOfView[ch.pos] && ch.invisible == 0 && ch.alignment != alignment && ch.alignment != Alignment.NEUTRAL){
						float chDist = ch.stealth() + distance(ch);
						if(ch instanceof Hero hero){
							int x=0;
							
							x=hero.惊醒距离();
							
							//silent steps rogue talent, which also applies to rogue's shadow clone
							if (ch instanceof Hero||ch instanceof ShadowClone.ShadowAlly){
								if (distance(ch) >= x) {//4old
									chDist = Float.POSITIVE_INFINITY;
								}
							}
						}
						//flying characters are naturally stealthy
						if (ch.flying && distance(ch) >= 2){
							chDist = Float.POSITIVE_INFINITY;
						}
						
						if (chDist < closestHostileDist){
							closestHostileDist = chDist;
						}
					}
				}

				if (Random.Float( closestHostileDist ) < 1) {
					
					awaken(enemyInFOV);
					if (state == SLEEPING){
						spend(TICK); //wait if we can't wake up for some reason
					}
					return true;
				}

			}

			enemySeen = false;
			spend( TICK );

			return true;
		}

		protected void awaken( boolean enemyInFOV ){
			if (enemyInFOV) {
				enemySeen = true;
				notice();
				state = HUNTING;
				target = enemy.pos;
			} else {
				notice();
				state = WANDERING;
				target = Dungeon.level.randomDestination( Mob.this );
			}

			if (alignment == Alignment.ENEMY && Dungeon.isChallenged(Challenges.SWARM_INTELLIGENCE)) {
				for (Mob mob : Dungeon.level.mobs) {
					if (mob.paralysed <= 0
							&& Dungeon.level.distance(pos, mob.pos) <= 8
							&& mob.state != mob.HUNTING) {
						mob.beckon(target);
					}
				}
			}
			spend(TIME_TO_WAKE_UP);
		}
	}

	protected class Wandering implements AiState {

		public static final String TAG	= "WANDERING";

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			if (enemyInFOV && (justAlerted || Random.Float( distance( enemy ) / 2f + enemy.stealth() ) < 1)) {

				return noticeEnemy();

			} else {

				return continueWandering();

			}
		}
		
		protected boolean noticeEnemy(){
			enemySeen = true;
			
			notice();
			alerted = true;
			state = HUNTING;
			target = enemy.pos;
			
			if (alignment == Alignment.ENEMY && Dungeon.isChallenged( Challenges.SWARM_INTELLIGENCE )) {
				for (Mob mob : Dungeon.level.mobs) {
					if (mob.paralysed <= 0
							&& Dungeon.level.distance(pos, mob.pos) <= 8
							&& mob.state != mob.HUNTING) {
						mob.beckon( target );
					}
				}
			}
			
			return true;
		}
		
		protected boolean continueWandering(){
			enemySeen = false;
			int oldPos = pos;
			if (target != -1 && getCloser( target )) {
				spend( 1 / 移速() );
				return moveSprite( oldPos, pos );
			} else {
				target = randomDestination();
				spend( TICK );
			}
			
			return true;
		}

		protected int randomDestination(){
			return Dungeon.level.randomDestination( Mob.this );
		}
		
	}

	//we keep a list of characters we were recently hit by, so we can switch targets if needed
	protected ArrayList<Char> recentlyAttackedBy = new ArrayList<>();

	protected class Hunting implements AiState {

		public static final String TAG	= "HUNTING";

		//prevents rare infinite loop cases

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = enemyInFOV;
			if (enemyInFOV && !isCharmedBy( enemy ) && canAttack( enemy )) {

				recentlyAttackedBy.clear();
				target = enemy.pos;
				return doAttack( enemy );

			} else {

				//if we cannot attack our target, but were hit by something else that
				// is visible and attackable or closer, swap targets
				if (handleRecentAttackers()){
					return act( true, justAlerted );
				}

				if (enemyInFOV) {
					target = enemy.pos;
				} else if (enemy == null) {
					
					sprite.showLost();
					state = WANDERING;
					target = ((Mob.Wandering)WANDERING).randomDestination();
					spend( TICK );
					return true;
				}
				
				int oldPos = pos;
				if (target != -1 && getCloser( target )) {
					
					spend( 1 / 移速() );
					return moveSprite( oldPos,  pos );

				} else {

				
					return handleUnreachableTarget(enemyInFOV, justAlerted);
				}
			}
			}
			protected boolean handleRecentAttackers(){
				boolean swapped = false;
				if (!recentlyAttackedBy.isEmpty()){
					for (Char ch : recentlyAttackedBy){
						if (ch != null && ch.isActive() && Actor.chars().contains(ch) && alignment != ch.alignment && fieldOfView[ch.pos] && ch.invisible == 0 && !isCharmedBy(ch)) {
							if (canAttack(ch) || enemy == null || Dungeon.level.distance(pos, ch.pos) < Dungeon.level.distance(pos, enemy.pos)) {
								enemy = ch;
								target = ch.pos;
								swapped = true;
							}
						}
					}
					recentlyAttackedBy.clear();
				}
				return swapped;
			}
		//prevents rare infinite loop cases
		protected boolean recursing = false;
		
		//Try to switch targets to another enemy that is closer or reachable
		//unless we have already done that and still can't move toward them, then move on.
		protected boolean handleUnreachableTarget(boolean enemyInFOV, boolean justAlerted){
			if (!recursing) {
				Char oldEnemy = enemy;
				enemy = null;
				enemy = chooseEnemy();
				if (enemy != null && enemy != oldEnemy) {
					recursing = true;
					boolean result = act(enemyInFOV, justAlerted);
					recursing = false;
					return result;
				}
			}
			
			spend( TICK );
			if (!enemyInFOV) {
				sprite.showLost();
				state = WANDERING;
				target = ((Mob.Wandering)WANDERING).randomDestination();
			}
			return true;
		}
	}

	protected class Fleeing implements AiState {

		public static final String TAG	= "FLEEING";

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = enemyInFOV;
			//triggers escape logic when 0-dist rolls a 6 or greater.
			if (enemy == null || !enemyInFOV && 1 + Random.Int(Dungeon.level.distance(pos, target)) >= 6){
				escaped();
				if (state != FLEEING){
					spend( TICK );
					return true;
				}
			
			//if enemy isn't in FOV, keep running from their previous position.
			} else if (enemyInFOV) {
				target = enemy.pos;
			}

			int oldPos = pos;
			if (target != -1 && getFurther( target )) {

				spend( 1 / 移速() );
				return moveSprite( oldPos, pos );

			} else {

				spend( TICK );
				nowhereToRun();

				return true;
			}
		}

		protected void escaped(){
			//does nothing by default, some enemies have special logic for this
		}

		//enemies will turn and fight if they have nowhere to run and aren't affect by terror
		protected void nowhereToRun() {
			if (buff( Terror.class ) == null && buff( Dread.class ) == null) {
				if (enemySeen) {
					sprite.showStatus(CharSprite.WARNING, Messages.get(Mob.class, "rage"));
					sprite.愤怒();//逃不掉了！
					state = HUNTING;
				} else {
					state = WANDERING;
				}
			}
		}
	}

	protected class Passive implements AiState {

		public static final String TAG	= "PASSIVE";

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = enemyInFOV;
			spend( TICK );
			return true;
		}
	}
	
	
	private static ArrayList<Mob> heldAllies = new ArrayList<>();

	public static void holdAllies( Level level ){
		holdAllies(level, Dungeon.hero.pos);
	}

	public static void holdAllies( Level level, int holdFromPos ){
		heldAllies.clear();
		for (Mob mob : level.mobs.toArray( new Mob[0] )) {
			//preserve directable allies or empowered intelligent allies no matter where they are
			if (mob instanceof DirectableAlly
				|| (mob.intelligentAlly && PowerOfMany.getPoweredAlly() == mob)) {
				if (mob instanceof DirectableAlly) {
					((DirectableAlly) mob).clearDefensingPos();
				}
				level.mobs.remove( mob );
				heldAllies.add(mob);
				
			//preserve other intelligent allies if they are near the hero
			} else if (mob.alignment == Alignment.ALLY
					&& mob.intelligentAlly
					&& Dungeon.level.distance(holdFromPos, mob.pos) <= 5){
				level.mobs.remove( mob );
				heldAllies.add(mob);
			}
		}
	}

	public static void restoreAllies( Level level, int pos ){
		restoreAllies(level, pos, -1);
	}

	public static void restoreAllies( Level level, int pos, int gravitatePos ){
		if (!heldAllies.isEmpty()){
			
			ArrayList<Integer> candidatePositions = new ArrayList<>();
			for (int i : PathFinder.NEIGHBOURS8) {
				if (!Dungeon.level.solid[i+pos] && !Dungeon.level.avoid[i+pos] && level.findMob(i+pos) == null){
					candidatePositions.add(i+pos);
				}
			}

			//gravitate pos sets a preferred location for allies to be closer to
			if (gravitatePos == -1) {
				Collections.shuffle(candidatePositions);
			} else {
				Collections.sort(candidatePositions, new Comparator<Integer>() {
					@Override
					public int compare(Integer t1, Integer t2) {
						return Dungeon.level.distance(gravitatePos, t1) -
								Dungeon.level.distance(gravitatePos, t2);
					}
				});
			}

			//can only have one empowered ally at once, prioritize incoming ally
			if (Stasis.getStasisAlly() != null){
				for (Mob mob : level.mobs.toArray( new Mob[0] )) {
					if (mob.buff(PowerOfMany.PowerBuff.class) != null){
						mob.buff(PowerOfMany.PowerBuff.class).detach();
					}
				}
			}
			
			for (Mob ally : heldAllies) {

				//can only have one empowered ally at once, prioritize incoming ally
				if (ally.buff(PowerOfMany.PowerBuff.class) != null){
					for (Mob mob : level.mobs.toArray( new Mob[0] )) {
						if (mob.buff(PowerOfMany.PowerBuff.class) != null){
							mob.buff(PowerOfMany.PowerBuff.class).detach();
						}
					}
				}

				level.mobs.add(ally);
				ally.state = ally.WANDERING;
				
				if (!candidatePositions.isEmpty()){
					ally.pos = candidatePositions.remove(0);
				} else {
					ally.pos = pos;
				}
				if (ally.sprite != null) ally.sprite.place(ally.pos);

				if (ally.fieldOfView == null || ally.fieldOfView.length != level.length()){
					ally.fieldOfView = new boolean[level.length()];
				}
				Dungeon.level.updateFieldOfView( ally, ally.fieldOfView );
				
			}
		}
		heldAllies.clear();
	}
	
	public static void clearHeldAllies(){
		heldAllies.clear();
	}
}


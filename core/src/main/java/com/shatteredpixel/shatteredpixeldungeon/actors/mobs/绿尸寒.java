

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.SacrificialFire;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.绿尸寒动画;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 绿尸寒 extends Ghoul {
	
	{
		spriteClass = 绿尸寒动画.class;
		
		生命 = 最大生命 = 70;
		defenseSkill = 25;
		
		经验 = 11;
		最大等级 = 22;
		
		SLEEPING = new Sleeping();
		WANDERING = new Wandering();
		state = SLEEPING;

		loot = new Gold().random(2);
		
		属性表.add(Property.UNDEAD);
		属性表.add(Property.活尸);
	}

	@Override
	public float 最小攻击() {
		return 20;
	}
	@Override
	public float 最大攻击() {
		return 26;
	}

	@Override
	public int 最大命中(Char target ) {
		return 28;
	}

	@Override
	public float 最大防御() {
		return super.最大防御()+6;
	}

	@Override
	public float spawningWeight() {
		return 0.5f;
	}

	private int timesDowned = 0;
	protected int partnerID = -1;
	int 死亡次数=0;
	private static final String 死亡次数x = "死亡次数";

	private static final String PARTNER_ID = "partner_id";
	private static final String TIMES_DOWNED = "times_downed";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( PARTNER_ID, partnerID );
		bundle.put( TIMES_DOWNED, timesDowned );
		bundle.put(死亡次数x, 死亡次数);
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		partnerID = bundle.getInt( PARTNER_ID );
		timesDowned = bundle.getInt( TIMES_DOWNED );
		死亡次数 = bundle.getInt(死亡次数x);
	}
	
	@Override
	protected boolean act() {
		//create a child
		if (partnerID == -1){
			
			ArrayList<Integer> candidates = new ArrayList<>();
			
			int[] neighbours = {pos + 1, pos - 1, pos + Dungeon.level.width(), pos - Dungeon.level.width()};
			for (int n : neighbours) {
				if (Dungeon.level.passable[n]
						&& Actor.findChar( n ) == null
						&& (!Char.hasProp(this, Property.LARGE) || Dungeon.level.openSpace[n])) {
					candidates.add( n );
				}
			}
			
			if (!candidates.isEmpty()){
				Sample.INSTANCE.play(Assets.Sounds.尸群);
				绿尸寒 child = new 绿尸寒();
				child.partnerID = this.id();
				this.partnerID = child.id();
				if (state != SLEEPING) {
					child.state = child.WANDERING;
				}
				
				child.pos = Random.element( candidates );

				GameScene.add( child );
				Dungeon.level.occupyCell(child);
				
				if (sprite.visible) {
					Actor.add( new Pushing( child, pos, child.pos ) );
				}

				//champion buff, mainly
				for (Buff b : buffs()){
					if (b.revivePersists) {
						Buff.施加(child, b.getClass());
					}
				}

			}
			
		}
		return super.act();
	}

	private boolean beingLifeLinked = false;

	@Override
	public void 死亡时(Object 来源) {
		死亡次数++;
		if (来源!=Chasm.class&&来源!=GhoulLifeLink.class&&!Dungeon.level.pit[pos]){
			绿尸寒 nearby = GhoulLifeLink.searchForHost(this);
			if (nearby != null){
				beingLifeLinked = true;
				timesDowned++;
				Actor.remove(this);
				Dungeon.level.mobs.remove( this );
				Buff.新增(nearby, GhoulLifeLink.class).set(timesDowned*5, this);
				((绿尸寒动画)sprite).crumple();
				return;
			}
		}

		super.死亡时(来源);
	}

	@Override
	public boolean isAlive() {
		return super.isAlive() || beingLifeLinked;
	}

	@Override
	public boolean isActive() {
		return !beingLifeLinked && isAlive();
	}

	@Override
	protected synchronized void onRemove() {
		if (beingLifeLinked) {
			for (Buff buff : buffs()) {
				if (buff instanceof SacrificialFire.Marked){
					//don't remove and postpone so marked stays on
					Buff.延长(this, SacrificialFire.Marked.class, timesDowned*5);
				} else if (buff.revivePersists) {
					//don't remove
				} else {
					buff.detach();
				}
			}
		} else {
			super.onRemove();
		}
	}

	private class Sleeping extends Mob.Sleeping {
		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			绿尸寒 partner = (绿尸寒) Actor.findById(partnerID);
			if (partner != null && partner.state != partner.SLEEPING){
				state = WANDERING;
				target = partner.pos;
				return true;
			} else {
				return super.act( enemyInFOV, justAlerted );
			}
		}
	}
	
	private class Wandering extends Mob.Wandering {
		
		@Override
		protected boolean continueWandering() {
			enemySeen = false;
			
			绿尸寒 partner = (绿尸寒) Actor.findById(partnerID);
			if (partner != null && (partner.state != partner.WANDERING ||Dungeon.level.距离(pos,partner.target)>1)){
				target = partner.pos;
				int oldPos = pos;
				if (getCloser( target )){
					spend( 1 / 移速() );
					return moveSprite( oldPos, pos );
				} else {
					spend( TICK );
					return true;
				}
			} else {
				return super.continueWandering();
			}
		}
	}

	public static class GhoulLifeLink extends Buff{

		private 绿尸寒 ghoul;
		private int turnsToRevive;

		@Override
		public boolean act() {
			if (target.alignment != ghoul.alignment){
				detach();
				return true;
			}

			if (target.fieldOfView == null){
				target.fieldOfView = new boolean[Dungeon.level.length()];
				Dungeon.level.updateFieldOfView( target, target.fieldOfView );
			}

			if (!target.fieldOfView[ghoul.pos] &&Dungeon.level.距离(ghoul.pos,target.pos)>=4){
				detach();
				return true;
			}

			if (Dungeon.level.pit[ghoul.pos]){
				super.detach();
				ghoul.beingLifeLinked = false;
				ghoul.死亡时(this);
				return true;
			}

			//have to delay this manually here are a downed ghouls can't be directly frozen otherwise
			if (target.buff(Challenge.DuelParticipant.class) == null) {
				turnsToRevive--;
			}
			if (turnsToRevive <= 0){
				if (Actor.findChar( ghoul.pos ) != null) {
					ArrayList<Integer> candidates = new ArrayList<>();
					for (int n : PathFinder.相邻) {
						int cell = ghoul.pos + n;
						if (Dungeon.level.passable[cell]
								&& Actor.findChar( cell ) == null
								&& (!Char.hasProp(ghoul, Property.LARGE) || Dungeon.level.openSpace[cell])) {
							candidates.add( cell );
						}
					}
					if (candidates.size() > 0) {
						int newPos = Random.element( candidates );
						Actor.add( new Pushing( ghoul, ghoul.pos, newPos ) );
						ghoul.pos = newPos;

					} else {
						spend(TICK);
						return true;
					}
				}
				float x=0.25f;
				x*=(float)Math.pow(0.9f,ghoul.死亡次数);
				ghoul.回血(ghoul.最大生命(Math.max(0.04f,x)));
				ghoul.beingLifeLinked = false;
				Actor.add(ghoul);
				ghoul.timeToNow();
				Dungeon.level.mobs.add(ghoul);
				Dungeon.level.occupyCell( ghoul );
				ghoul.sprite.idle();
				if (ghoul.enemy != null && ghoul.enemy.alignment == ghoul.alignment){
					ghoul.enemy = null; //reset enemy
				}
				super.detach();
				return true;
			}

			spend(TICK);
			return true;
		}

		public void updateVisibility(){
			if (ghoul != null && ghoul.sprite != null){
				ghoul.sprite.visible = Dungeon.level.heroFOV[ghoul.pos];
			}
		}

		public void set(int turns, 绿尸寒 ghoul){
			this.ghoul = ghoul;
			turnsToRevive = turns;
		}

		@Override
		public void fx(boolean on) {
			if (on && ghoul != null && ghoul.sprite == null){
				GameScene.addSprite(ghoul);
				((绿尸寒动画)ghoul.sprite).crumple();
			}
		}

		@Override
		public void detach() {
			super.detach();
			绿尸寒 newHost = searchForHost(ghoul);
			if (newHost != null){
				attachTo(newHost);
				timeToNow();
			} else {
				ghoul.beingLifeLinked = false;
				ghoul.死亡时(this);
			}
		}

		private static final String GHOUL = "ghoul";
		private static final String LEFT  = "left";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(GHOUL, ghoul);
			bundle.put(LEFT, turnsToRevive);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			ghoul = (绿尸寒) bundle.get(GHOUL);
			ghoul.beingLifeLinked = true;
			turnsToRevive = bundle.getInt(LEFT);
		}

		public static 绿尸寒 searchForHost(绿尸寒 dieing){

			for (Char ch : Actor.chars()){
				//don't count hero ally ghouls or duel frozen ghouls
				if (ch != dieing && ch instanceof 绿尸寒
						&& ch.alignment == dieing.alignment
						&& ch.buff(Challenge.SpectatorFreeze.class) == null){
					if (ch.fieldOfView == null){
						ch.fieldOfView = new boolean[Dungeon.level.length()];
						Dungeon.level.updateFieldOfView( ch, ch.fieldOfView );
					}
					if (ch.fieldOfView[dieing.pos] ||Dungeon.level.距离(ch.pos,dieing.pos)<4){
						return (绿尸寒) ch;
					}
				}
			}
			return null;
		}
	}
}

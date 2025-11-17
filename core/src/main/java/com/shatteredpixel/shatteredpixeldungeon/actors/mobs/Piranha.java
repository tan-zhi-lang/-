

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Levitation;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.food.ChargrilledMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.food.FrozenCarpaccio;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.food.StewedMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.RatSkull;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PiranhaSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Piranha extends Mob {
	
	{
		spriteClass = PiranhaSprite.class;

		baseSpeed = 2f;
		
		经验 = 0;
		
		loot = MysteryMeat.class;
		
		
		SLEEPING = new Sleeping();
		WANDERING = new Wandering();
		HUNTING = new Hunting();
		
		state = SLEEPING;

	}
	
	public Piranha() {
		super();
		
		生命 = 最大生命 = 10 + Dungeon.depth * 5;
		defenseSkill = 10 + Dungeon.depth * 2;
	}
	
	@Override
	protected boolean act() {
		
		if (!Dungeon.level.water[pos] || flying) {
			if (sprite != null && buff(Levitation.class) != null){
				sprite.emitter().burst(Speck.factory( Speck.JET ), 10);
			}
			dieOnLand();
			return true;
		} else {
			return super.act();
		}
	}
	
	@Override
	public int 攻击时(Char enemy,int damage){
		Sample.INSTANCE.play(Assets.Sounds.鱼叫);
		return super.攻击时(enemy,damage);
	}
	@Override
	public int 最小攻击() {
		return Dungeon.depth;
	}
	
	@Override
	public int 最大攻击() {
		return  4 + Dungeon.depth * 2 ;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 20 + Dungeon.depth * 2;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+Dungeon.depth;
	}

	@Override
	public boolean surprisedBy(Char enemy, boolean attacking) {
		if (enemy == Dungeon.hero && (!attacking || ((Hero)enemy).canSurpriseAttack())){
			if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
				fieldOfView = new boolean[Dungeon.level.length()];
				Dungeon.level.updateFieldOfView( this, fieldOfView );
			}
			return state == SLEEPING || !fieldOfView[enemy.pos] || enemy.invisible > 0;
		}
		return super.surprisedBy(enemy, attacking);
	}

	public void dieOnLand(){
		死亡时( null );
	}

	@Override
	public void 死亡时(Object cause ) {
		super.死亡时( cause );
		
		Statistics.piranhasKilled++;
		Badges.validatePiranhasKilled();
	}

	@Override
	public float spawningWeight() {
		return 0;
	}

	@Override
	public boolean reset() {
		return true;
	}
	
	@Override
	protected boolean getCloser( int target ) {
		
		if (rooted) {
			return false;
		}
		
		int step = Dungeon.findStep( this, target, BArray.and(Dungeon.level.water, Dungeon.level.passable, null), fieldOfView, true );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected boolean getFurther( int target ) {
		int step = Dungeon.flee( this, target, BArray.and(Dungeon.level.water, Dungeon.level.passable, null), fieldOfView, true );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	{
		for (Class c : new BlobImmunity().immunities()){
			if (c != Electricity.class && c != Freezing.class){
				immunities.add(c);
			}
		}
		immunities.add( 燃烧.class );
	}
	
	//if there is not a path to the enemy, piranhas act as if they can't see them
	private class Sleeping extends Mob.Sleeping{
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			if (enemyInFOV) {
				PathFinder.buildDistanceMap(enemy.pos, Dungeon.level.water, viewDistance);
				enemyInFOV = PathFinder.distance[pos] != Integer.MAX_VALUE;
			}
			
			return super.act(enemyInFOV, justAlerted);
		}
	}
	
	private class Wandering extends Mob.Wandering{
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			寻找肉();
			if (enemyInFOV) {
				PathFinder.buildDistanceMap(enemy.pos, Dungeon.level.water, viewDistance);
				enemyInFOV = PathFinder.distance[pos] != Integer.MAX_VALUE;
			}
			
			return super.act(enemyInFOV, justAlerted);
		}
	}
	
	private class Hunting extends Mob.Hunting{
		
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			寻找肉();
			if (enemyInFOV) {
				PathFinder.buildDistanceMap(enemy.pos, Dungeon.level.water, viewDistance);
				enemyInFOV = PathFinder.distance[pos] != Integer.MAX_VALUE;
			}
			
			return super.act(enemyInFOV, justAlerted);
		}
	}
	public void 寻找肉(){
		if(true){
			return;
		}
		try{
			for(int n: PathFinder.范围4){
				Heap heap=Dungeon.level.heaps.get(pos+n);
				if(heap!=null&&heap.type==Heap.Type.HEAP){
					Item item=heap.peek();
					if(item instanceof MysteryMeat||item instanceof StewedMeat||item instanceof ChargrilledMeat||item instanceof FrozenCarpaccio){
						target=heap.pos;
						if(pos==heap.pos){
							heap.destroy();
							
							try{
								boolean newp = true;
								for (int nx : PathFinder.NEIGHBOURS8){
									if(newp){
										Piranha piranha = Piranha.random();
										piranha.pos=pos+nx;
										if(Dungeon.level.map[piranha.pos]==Terrain.WATER&&
										   Dungeon.level.findMob( piranha.pos )==null){
											newp=false;
											GameScene.add(piranha,1);
											Actor.add(piranha);
											Dungeon.level.occupyCell(piranha);
										}
									}
								}
							}catch(Exception e){
							
							}
						}
					}
				}
			}
		}catch(Exception e){
		
		}
	}
	public static Piranha random(){
		float altChance = 1/50f * RatSkull.exoticChanceMultiplier();
		if (Random.Float() < altChance){
			return new PhantomPiranha();
		} else {
			return new Piranha();
		}
	}
}

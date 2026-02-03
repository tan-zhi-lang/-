

package com.shatteredpixel.shatteredpixeldungeon.items.bombs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ArcaneBomb extends Bomb {
	
	{
		image = 物品表.ARCANE_BOMB;
	}

	@Override
	public boolean explodesDestructively() {
		return false;
	}

	@Override
	protected int explosionRange() {
		return 2;
	}

	@Override
	protected Fuse createFuse() {
		return new ArcaneBombFuse();
	}
	
	@Override
	public void explode(int cell) {
		super.explode(cell);
		
		ArrayList<Char> affected = new ArrayList<>();
		
		PathFinder.buildDistanceMap( cell, BArray.not( Dungeon.level.solid, null ), explosionRange() );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
					CellEmitter.get(i).burst(ElmoParticle.FACTORY, 10);
				Char ch = Actor.findChar(i);
				if (ch != null){
					affected.add(ch);
				}
			}
		}
		
		for (Char ch : affected){
			//pierces armor, and damage in 5x5 instead of 3x3
			int damage = Math.round(Random.NormalIntRange( 4 + Dungeon.scalingDepth(), 12 + 3*Dungeon.scalingDepth() ));
			ch.受伤时(damage, this);
			if (ch == Dungeon.hero && !ch.isAlive()){
				Badges.validateDeathFromFriendlyMagic();
				Dungeon.fail(this);
			}
		}
	}
	
	@Override
	public int 金币() {
		//prices of ingredients
		return quantity * (20 + 30);
	}

	//normal fuse logic, but with particle effects
	public static class ArcaneBombFuse extends Fuse {

		private ArrayList<Emitter> gooWarnEmitters = new ArrayList<>();

		//particle effect addition is delayed using an actor to ensure things like gamescene is set up when loading
		@Override
		public Fuse ignite(Bomb bomb) {
			super.ignite(bomb);
			Actor.add(new Actor() {
				{ actPriority = VFX_PRIO; }
				@Override
				protected boolean act() {
					int bombPos = -1;
					for (Heap heap : Dungeon.level.heaps.valueList()) {
						if (heap.items.contains(bomb)) {
							bombPos = heap.pos;
						}
					}
					if (bombPos != -1) {
						PathFinder.buildDistanceMap(bombPos, BArray.not(Dungeon.level.solid, null), bomb.explosionRange());
						for (int i = 0; i < PathFinder.distance.length; i++) {
							if (PathFinder.distance[i] < Integer.MAX_VALUE) {
								Emitter
										e = CellEmitter.get(i);
								if (e != null) {
									e.pour(GooSprite.GooParticle.FACTORY,0.03f);
									gooWarnEmitters.add(e);
								}
							}
						}
					}
					Actor.remove(this);
					return true;
				}
			});
			return this;
		}

		@Override
		public void snuff() {
			super.snuff();
			for (Emitter e : gooWarnEmitters) {
				e.on = false;
			}
			gooWarnEmitters.clear();
		}
	}
}

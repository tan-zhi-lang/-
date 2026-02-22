

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blizzard;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SnowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Honeypot;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.液火药剂;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.Room;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.EmptyRoom;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Firebloom;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class 魔法冰霜房间 extends SpecialRoom {

	@Override
	public int minWidth() { return 7; }
	public int minHeight() { return 7; }

	@Override
	public void paint(Level level) {

		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY );

		Door door = entrance();
		door.set( Door.Type.REGULAR );

		Point firePos = center();
		Room behindFire = new EmptyRoom();

		if (door.x == left || door.x == right){
			firePos.y = top+1;
			while (firePos.y != bottom){
				Blob.seed(level.pointToCell(firePos),1,魔法冰霜.class,level);
				Painter.set(level, firePos, Terrain.EMPTY_SP);
				firePos.y++;
			}
			if (door.x == left){
				behindFire.set(firePos.x+1, top+1, right-1, bottom-1);
			} else {
				behindFire.set(left+1, top+1, firePos.x-1, bottom-1);
			}
		} else {
			firePos.x = left+1;
			while (firePos.x != right){
				Blob.seed(level.pointToCell(firePos),1,魔法冰霜.class,level);
				Painter.set(level, firePos, Terrain.EMPTY_SP);
				firePos.x++;
			}
			if (door.y == top){
				behindFire.set(left+1, firePos.y+1, right-1, bottom-1);
			} else {
				behindFire.set(left+1, top+1, right-1, firePos.y-1);
			}
		}

		Painter.fill(level, behindFire, Terrain.EMPTY_SP);

		boolean honeyPot = Random.Int( 2 ) == 0;

		int n = Random.IntRange( 3, 4 );

		for (int i=0; i < n; i++) {
			int pos;
			do {
				pos = level.pointToCell(behindFire.random(0));
			} while (level.heaps.get(pos) != null);
			if (honeyPot){
				level.drop( new Honeypot(), pos);
				honeyPot = false;
			} else
				level.drop( prize( level ), pos );
		}

		level.addItemToSpawn(new 液火药剂().房间物品());
		level.addItemToSpawn( new Firebloom.Seed().房间物品());

	}

	private static Item prize( Level level ) {

		if (Random.Int(3) != 0){
			Item prize = level.findPrizeItem();
			if (prize != null)
				return prize;
		}

		return Generator.random( Random.oneOf(
				Generator.Category.POTION,
				Generator.Category.SCROLL,
				Generator.Category.FOOD,
				Generator.Category.GOLD
		) );
	}

	@Override
	public boolean canPlaceGrass(Point p) {
		return false;
	}

	@Override
	public boolean canPlaceCharacter(Point p, Level l) {
		Blob fire = l.blobs.get(魔法冰霜.class);

		//disallow placing on special tiles or next to fire if fire is present.
		//note that this is slightly brittle, assumes the fire is either all there or totally gone
		if (fire != null && fire.volume > 0){
			int cell = l.pointToCell(p);
			if (l.map[cell] == Terrain.EMPTY_SP) return false;

			if (fire.cur[cell] > 0)     return false;
			for (int i : PathFinder.相邻4){
				if (fire.cur[cell+i] > 0)   return false;
			}
		}

		return super.canPlaceCharacter(p, l);
	}

	public static class 魔法冰霜 extends Blob {

		@Override
		protected void evolve() {

			int cell;

			Freezing freeze = (Freezing)Dungeon.level.blobs.get( Freezing.class );
			Blizzard
					bliz = (Blizzard)Dungeon.level.blobs.get(Blizzard.class);

			Freezing
					free = (Freezing)Dungeon.level.blobs.get(Freezing.class);

			//if any part of the free is cleared, cleanse the whole thing
			//Note that this is a bit brittle atm, it assumes only one group of eternal free per floor
			boolean clearAll = false;

			Level l = Dungeon.level;
			for (int i = area.left-1; i <= area.right; i++) {
				for (int j = area.top-1; j <= area.bottom; j++) {
					cell = i + j*l.width();
					if (cur[cell] > 0) {

						//evaporates in the presence of water, frost, or blizzard
						//this blob is not considered interchangeable with free, so those blobs do not interact with it otherwise
						//potion of purity can cleanse it though
						if (l.water[cell]){
							cur[cell] = 0;
							clearAll = true;
						}
						//overrides free
						if (free != null && free.volume > 0 && free.cur[cell] > 0){
							free.clear(cell);
						}

						//clears itself if there is frost/blizzard on or next to it
						for (int k : PathFinder.自相邻8) {
							if (freeze != null && freeze.volume > 0 && freeze.cur[cell+k] > 0) {
								freeze.clear(cell);
								cur[cell] = 0;
								clearAll = true;
							}
							if (bliz != null && bliz.volume > 0 && bliz.cur[cell+k] > 0) {
								bliz.clear(cell);
								cur[cell] = 0;
								clearAll = true;
							}
						}
						l.passable[cell] = cur[cell] == 0 && (Terrain.flags[l.map[cell]] & Terrain.PASSABLE) != 0;
					}

					if (cur[cell] > 0
							|| cur[cell-1] > 0
							|| cur[cell+1] > 0
							|| cur[cell-Dungeon.level.width()] > 0
							|| cur[cell+Dungeon.level.width()] > 0) {

						Char ch = Actor.findChar( cell );
						if (ch != null) {
							if (Dungeon.level.water[ch.pos]){
								Buff.延长(ch, Frost.class, Frost.DURATION * 3);
							} else {
								Buff.延长(ch, Frost.class, Frost.DURATION);
							}
						}
						Heap heap = Dungeon.level.heaps.get( cell );
						if (heap != null) {
							heap.freeze();
						}
					}

					off[cell] = cur[cell];
						volume += off[cell];
					}
				}
			if (clearAll){
				fullyClear();
				return;
			}
		}

		public static void freeze( int cell ){
			Char
					ch = Actor.findChar(cell);
			if (ch != null && !ch.免疫(Freezing.class)) {
				if (ch.buff(Frost.class) != null){
					Buff.施加(ch,Frost.class,2f);
				} else {
					Chill
							chill = ch.buff(Chill.class);
					float turnsToAdd = Dungeon.level.water[cell] ? 5f : 3f;
					if (chill != null){
						float chillToCap = Chill.DURATION - chill.cooldown();
						chillToCap /= ch.resist(Chill.class); //account for resistance to chill
						turnsToAdd = Math.min(turnsToAdd, chillToCap);
					}
					if (turnsToAdd > 0f) {
						Buff.施加(ch, Chill.class, turnsToAdd);
					}
					if (chill != null
						&& chill.cooldown() >= Chill.DURATION/2 &&
						!ch.免疫(Frost.class)){
						Buff.施加(ch, Frost.class, Frost.DURATION);
					}
				}
			}

			Heap
					heap = Dungeon.level.heaps.get(cell);
			if (heap != null) heap.freeze();
		}

		@Override
		public void use( BlobEmitter emitter) {
			super.use( emitter );
			emitter.start(SnowParticle.FACTORY,0.05f,0);
		}

		@Override
		public String tileDesc() {
			return Messages.get(this,"desc");
		}

		//legacy functionality from before this was a proper blob. Returns true if this cell is visible
		public static boolean affect( int cell ) {

			Char ch = Actor.findChar( cell );
			if (ch != null) {
				if (Dungeon.level.water[ch.pos]){
					Buff.延长(ch, Frost.class, Frost.DURATION * 3);
				} else {
					Buff.延长(ch, Frost.class, Frost.DURATION);
				}
			}

			Heap heap = Dungeon.level.heaps.get( cell );
			if (heap != null) {
				heap.freeze();
			}

			if (Dungeon.level.heroFOV[cell]) {
				CellEmitter.get(cell).start(SnowParticle.FACTORY,0.2f,6);
				return true;
			} else {
				return false;
			}
		}



		@Override
		public void seed(Level level, int cell, int amount) {
			super.seed(level, cell, amount);
			level.passable[cell] = cur[cell] == 0 && (Terrain.flags[level.map[cell]] & Terrain.PASSABLE) != 0;
		}

		@Override
		public void clear(int cell) {
			if (volume > 0 && cur[cell] > 0) {
				fullyClear();
			}
		}

		@Override
		public void fullyClear() {
			super.fullyClear();
			Dungeon.level.buildFlagMaps();
		}


		@Override
		public void onBuildFlagMaps( Level l ) {
			if (volume > 0){
				for (int i=0; i < l.length(); i++) {
					l.passable[i] = l.passable[i] && cur[i] == 0;
				}
			}
		}
	}

}

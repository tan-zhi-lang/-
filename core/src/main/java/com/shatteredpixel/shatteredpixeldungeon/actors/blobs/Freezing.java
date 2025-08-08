

package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SnowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.MagicalFireRoom;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

public class Freezing extends Blob {
	
	@Override
	protected void evolve() {
		
		int cell;
		
		Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );
		
		for (int i = area.left-1; i <= area.right; i++) {
			for (int j = area.top-1; j <= area.bottom; j++) {
				cell = i + j*Dungeon.level.width();
				if (cur[cell] > 0) {
					
					if (fire != null && fire.volume > 0 && fire.cur[cell] > 0){
						fire.clear(cell);
						off[cell] = cur[cell] = 0;
						continue;
					}
					
					Freezing.freeze(cell);
					
					off[cell] = cur[cell] - 1;
					volume += off[cell];
				} else {
					off[cell] = 0;
				}
			}
		}
	}
	
	public static void freeze( int cell ){
		Char ch = Actor.findChar( cell );
		if (ch != null && !ch.isImmune(Freezing.class)) {
			if (ch.buff(Frost.class) != null){
				Buff.施加(ch, Frost.class, 2f);
			} else {
				Chill chill = ch.buff(Chill.class);
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
						&& chill.cooldown() >= Chill.DURATION &&
						!ch.isImmune(Frost.class)){
					Buff.施加(ch, Frost.class, Frost.DURATION);
				}
			}
		}
		
		Heap heap = Dungeon.level.heaps.get( cell );
		if (heap != null) heap.freeze();
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.start( SnowParticle.FACTORY, 0.05f, 0 );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
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

		Fire fire = (Fire) Dungeon.level.blobs.get(Fire.class);
		if (fire != null && fire.volume > 0) {
			fire.clear( cell );
		}

		MagicalFireRoom.EternalFire eternalFire = (MagicalFireRoom.EternalFire)Dungeon.level.blobs.get(MagicalFireRoom.EternalFire.class);
		if (eternalFire != null && eternalFire.volume > 0) {
			eternalFire.clear( cell );
		}
		
		Heap heap = Dungeon.level.heaps.get( cell );
		if (heap != null) {
			heap.freeze();
		}
		
		if (Dungeon.level.heroFOV[cell]) {
			CellEmitter.get( cell ).start( SnowParticle.FACTORY, 0.2f, 6 );
			return true;
		} else {
			return false;
		}
	}
}

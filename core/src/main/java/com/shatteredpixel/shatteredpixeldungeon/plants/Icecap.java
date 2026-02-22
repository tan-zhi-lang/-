

package com.shatteredpixel.shatteredpixeldungeon.plants;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FrostImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.MagicalFireRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.PathFinder;

public class Icecap extends Plant {
	
	{
		image = 4;
		seedClass = Seed.class;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch instanceof Hero hero&&hero.职业精通()&&hero.subClass==HeroSubClass.守望者){
			Buff.施加(ch, FrostImbue.class, FrostImbue.DURATION*0.3f);
		}

		for (int i : PathFinder.自相邻8){
			if (!Dungeon.level.solid[pos+i]) {
				Freezing.affect( pos+i );
				if (Actor.findChar(pos+i) instanceof Mob){
					Buff.延长(Actor.findChar(pos+i), Trap.HazardAssistTracker.class, Trap.HazardAssistTracker.DURATION);
				}
			}
		}
		MagicalFireRoom.EternalFire eternalFire = (MagicalFireRoom.EternalFire)Dungeon.level.blobs.get(MagicalFireRoom.EternalFire.class);
		if (eternalFire != null && eternalFire.volume > 0) {
			eternalFire.clear( pos );
		}
	}
	public static class Seed extends Plant.Seed {
		{
			image = 物品表.SEED_ICECAP;

			plantClass = Icecap.class;
		}
		@Override
		protected void onThrow( int cell ) {
			MagicalFireRoom.EternalFire eternalFire = (MagicalFireRoom.EternalFire)Dungeon.level.blobs.get(MagicalFireRoom.EternalFire.class);
			Fire fire = (Fire)Dungeon.level.blobs.get(Fire.class);
			if ((eternalFire != null && eternalFire.volume > 0)||(fire != null && fire.volume > 0)) {
				Plant plant=new Icecap();
				plant.pos=cell;
				plant.activate(null);
			}else {
				super.onThrow(cell);
			}
		}
	}
}

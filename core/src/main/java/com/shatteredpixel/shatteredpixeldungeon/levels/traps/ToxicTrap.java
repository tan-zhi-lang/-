

package com.shatteredpixel.shatteredpixeldungeon.levels.traps;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class ToxicTrap extends Trap{

	{
		color = GREEN;
		shape = GRILL;
	}

	@Override
	public void activate() {
		
		for (int offset : PathFinder.NEIGHBOURS9){
			if (!Dungeon.level.solid[pos+offset]) {
				GameScene.add( Blob.seed( pos+offset, 33 + 2 * scalingDepth()/*300 + 20 * scalingDepth()*/, ToxicGas.class));
			}
		}
		
		Sample.INSTANCE.play(Assets.Sounds.GAS);

		for( int i : PathFinder.NEIGHBOURS9) {
			if (Actor.findChar(pos+i) instanceof Mob){
				Buff.延长(Actor.findChar(pos+i), Trap.HazardAssistTracker.class, HazardAssistTracker.DURATION);
			}
		}

	}
}

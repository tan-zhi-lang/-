

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.entrance;

import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.ChasmRoom;
import com.watabou.utils.PathFinder;

public class ChasmEntranceRoom extends ChasmRoom {

	@Override
	public int minHeight() {
		return Math.max(7, super.minHeight());
	}

	@Override
	public int minWidth() {
		return Math.max(7, super.minWidth());
	}

	@Override
	public float[] sizeCatProbs() {
		return new float[]{2, 1, 0};
	}

	@Override
	public boolean isEntrance() {
		return true;
	}

	@Override
	public void paint(Level level) {
		super.paint(level);

		int entrance;
		int tries = 30;
		boolean valid;
		do {
			entrance = level.pointToCell(random(2));

			//need extra logic here as these rooms can spawn small and cramped in very rare cases
			if (tries-- > 0){
				valid = level.map[entrance] != Terrain.CHASM && level.findMob(entrance) == null;
			} else {
				valid = false;
				for (int i : PathFinder.NEIGHBOURS4){
					if (level.map[entrance+i] != Terrain.CHASM){
						valid = true;
					}
				}
				valid = valid && level.findMob(entrance) == null;
			}
		} while (!valid);
		Painter.set( level, entrance, Terrain.ENTRANCE );

		for (int i : PathFinder.NEIGHBOURS8){
			Painter.set( level, entrance+i, Terrain.EMPTY );
		}

		level.transitions.add(new LevelTransition(level, entrance, LevelTransition.Type.REGULAR_ENTRANCE));
	}

}

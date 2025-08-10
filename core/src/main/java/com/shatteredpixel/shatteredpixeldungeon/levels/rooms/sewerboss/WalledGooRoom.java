

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.sewerboss;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.粘咕;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;

public class WalledGooRoom extends GooBossRoom {
	
	@Override
	public void paint(Level level) {
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1 , Terrain.EMPTY_SP );
		Painter.fill( level, this, 2 , Terrain.EMPTY );

		int pillarW = (width()-6)/2;
		int pillarH = (height()-6)/2;
		
		Painter.fill(level, left+2, top+2, pillarW, 1, Terrain.WALL);
		Painter.fill(level, left+2, top+2, 1, pillarH, Terrain.WALL);
		
		Painter.fill(level, left+2, bottom-2, pillarW, 1, Terrain.WALL);
		Painter.fill(level, left+2, bottom-1-pillarH, 1, pillarH, Terrain.WALL);
		
		Painter.fill(level, right-1-pillarW, top+2, pillarW, 1, Terrain.WALL);
		Painter.fill(level, right-2, top+2, 1, pillarH, Terrain.WALL);
		
		Painter.fill(level, right-1-pillarW, bottom-2, pillarW, 1, Terrain.WALL);
		Painter.fill(level, right-2, bottom-1-pillarH, 1, pillarH, Terrain.WALL);
		
		for (Door door : connected.values()) {
			door.set(Door.Type.REGULAR);
		}
		
		Painter.fill( level, left + width()/2 - 1, top + height()/2 - 2, 2 + width()%2, 4 + height()%2, Terrain.WATER);
		Painter.fill( level, left + width()/2 - 2, top + height()/2 - 1, 4 + width()%2, 2 + height()%2, Terrain.WATER);
		
		setupGooNest(level);
		
		粘咕 boss = new 粘咕();
		boss.pos = level.pointToCell(center());
		level.mobs.add( boss );
	}
	
	@Override
	public boolean canPlaceWater(Point p) {
		return false;
	}
}

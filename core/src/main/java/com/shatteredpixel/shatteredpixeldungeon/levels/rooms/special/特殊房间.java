

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;

public class 特殊房间 extends SpecialRoom {

	@Override
	public int minWidth() { return 10; }
	@Override
	public int minHeight() { return 10; }

	private static short[] 下水道1 = {
			2,4,2,2,4,2,2,4,2,

	};
	@Override
	public void paint(Level level) {
		Painter.fill(level,this,Terrain.WALL);
		Painter.fill( level, this, 1, Terrain.CHASM );
		Point c=center();

		Door door = entrance();
		door.set( Door.Type.REGULAR );

		for (int i = 0; i < 下水道1.length; i++){
			Painter.set(level,level.pointToCell(topLeft())+i,下水道1[i]);
		}
//		for (int i=0; i < 15; i++) {
//			for (int j=0; j < 15; j++) {
//				if(level.map[i * width() + j]==7){
//					level.entrance=i * width() + j;
//				}
//				if(level.map[i * width() + j]==8){
//					level.exit=i * width() + j;
//				}
//			}
//		}
	}

}

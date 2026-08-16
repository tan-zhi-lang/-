

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.tiles.CustomTilemap;
import com.watabou.noosa.Tilemap;
import com.watabou.utils.Point;

public class 异世界传送门房 extends SpecialRoom {

	@Override
	public int minWidth() { return 9; }
	public int minHeight() { return 9; }

	@Override
	public void paint(Level level) {
		Painter.fill(level,this,Terrain.WALL);
		Painter.fill( level, this, 1, Terrain.EMPTY );
		Point c=center();

		Door door = entrance();
		door.set( Door.Type.REGULAR );

		CustomTilemap vis=new 异世界传送门();
		if(vis!=null){
			vis.pos(c.x-1,c.y-1);
			level.customTiles.add(vis);
		}

	}

	public static class 异世界传送门 extends CustomTilemap{

		{
			texture = Assets.Environment.异世界传送门;

			tileW = tileH = 3;
		}

		final int TEX_WIDTH = 48;

		@Override
		public Tilemap create() {
			Tilemap v = super.create();
			v.map(mapSimpleImage(0, 0, TEX_WIDTH), 3);
			return v;
		}
//		@Override
//		public Image image(int tileX,int tileY) {
//			//only center 3x3 gives custom image/message
//			if (tileX >= 1 && tileX < 4 && tileY >= 1 && tileY < 4){
//				return super.image(tileX, tileY);
//			} else {
//				return null;
//			}
//		}

		@Override
		public String name(int tileX, int tileY) {
			return "异世界传送门";
		}

		@Override
		public String desc(int tileX, int tileY) {
			return "";
		}
	}
}

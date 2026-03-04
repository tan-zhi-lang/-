

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.传送阵眼;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.tiles.CustomTilemap;
import com.watabou.noosa.Tilemap;
import com.watabou.utils.Point;

public class 矮人传送魔法阵 extends SpecialRoom {

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

		CustomTilemap vis=null;
		if(Dungeon.相对层数()==11)vis=new 传送魔法阵11();
		if(Dungeon.相对层数()==18)vis=new 传送魔法阵18();
		if(vis!=null){
			vis.pos(c.x-1,c.y-1);
			level.customTiles.add(vis);
		}
		传送阵眼 x = new 传送阵眼();
		x.pos = c.x + c.y * level.width();
		level.mobs.add( x );
	}

	public static class 传送魔法阵11 extends CustomTilemap{

		{
			texture = Assets.Environment.矮人传送魔法阵11;

			tileW = tileH = 3;
		}

		final int TEX_WIDTH = 48;

		@Override
		public Tilemap create() {
			Tilemap v = super.create();
			v.map(mapSimpleImage(0, 0, TEX_WIDTH), 3);
			return v;
		}

		@Override
		public String name(int tileX, int tileY) {
			return "矮人传送魔法阵";
		}

		@Override
		public String desc(int tileX, int tileY) {
			return "需要消耗1个矮人徽章来传送";
		}
	}
	public static class 传送魔法阵18 extends CustomTilemap{

		{
			texture = Assets.Environment.矮人传送魔法阵18;

			tileW = tileH = 3;
		}

		final int TEX_WIDTH = 48;

		@Override
		public Tilemap create() {
			Tilemap v = super.create();
			v.map(mapSimpleImage(0, 0, TEX_WIDTH), 3);
			return v;
		}

		@Override
		public String name(int tileX, int tileY) {
			return "矮人传送魔法阵";
		}

		@Override
		public String desc(int tileX, int tileY) {
			return "";
		}
	}
}

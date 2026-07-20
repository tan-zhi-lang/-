

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.永生秘药;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.tiles.CustomTilemap;
import com.watabou.noosa.Tilemap;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.HashSet;
import java.util.Set;

public class 干涸大井 extends SpecialRoom {

	@Override
	public int minWidth() { return 9; }
	public int minHeight() { return 9; }

	@Override
	public void paint(Level level) {
		Painter.fill(level,this,Terrain.WALL);
		Painter.fill( level, this, 1, Terrain.EMPTY );
		Point c=center();
		int cpos=level.pointToCell(c);
		Door door = entrance();
		door.set( Door.Type.REGULAR );

		CustomTilemap vis=null;
		if(Dungeon.相对层数()==13)vis=new 干涸的大井();

		Set<Integer> 范围1Set = new HashSet<>();
		for (int i : PathFinder.相邻) 范围1Set.add(i);

		for (int n : PathFinder.范围2){

			if (范围1Set.contains(n)) {
				// 圆内
			}else {
				if(Random.Int(4)==0)level.drop(Generator.randomPotion(),cpos+n);
			}

		}
		if(vis!=null){

			level.drop(new 永生秘药(),cpos);

			vis.pos(c.x-1,c.y-1);
			level.customTiles.add(vis);
		}

	}

	public static class 干涸的大井 extends CustomTilemap{

		{
			texture = Assets.Environment.干涸的大井;

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
			return "干涸的大井";
		}

		@Override
		public String desc(int tileX, int tileY) {
			return "";
		}
	}

}

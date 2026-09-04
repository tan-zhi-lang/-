

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Statue;
import com.shatteredpixel.shatteredpixeldungeon.items.food.金苹果;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.星之果实;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;

public class 星之果实神殿 extends SpecialRoom {

	@Override
	public int minWidth() { return 9; }
	public int minHeight() { return 9; }

	@Override
	public void paint(Level level) {
		Painter.fill(level,this,Terrain.WALL);
		Painter.fill( level, this, 1, Terrain.EMPTY );
		Point c=center();

		Door door = entrance();
		door.set( Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey(Dungeon.depth ));

		int pos=c.x + c.y * level.width();

		level.drop(new 星之果实(),pos);

		Statue x = new Statue().random(false);
		x.pos = level.左上边(pos);
		x.state=x.HUNTING;
		level.mobs.add( x );

		Statue x2 = new Statue().random(false);
		x2.pos = level.右上边(pos);
		x2.state=x2.HUNTING;
		level.mobs.add( x2 );

		Statue x3 = new Statue().random(false);
		x3.pos = level.左下边(pos);
		x3.state=x3.HUNTING;
		level.mobs.add( x3 );

		Statue x4 = new Statue().random(false);
		x4.pos = level.右下边(pos);
		x4.state=x4.HUNTING;
		level.mobs.add( x4 );
	}

}

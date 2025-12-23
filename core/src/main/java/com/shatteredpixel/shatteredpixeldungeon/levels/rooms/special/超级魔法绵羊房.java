

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.超级魔法绵羊;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.utils.Point;

public class 超级魔法绵羊房 extends SpecialRoom {

	public void paint( Level level ) {

		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY );

		Point c = center();
		int cx = c.x;
		int cy = c.y;
		
		Door door = entrance();
		
		door.set( Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey( Dungeon.depth ) );

		level.addItemToSpawn( new PotionOfMindVision());
		
		超级魔法绵羊 statue = new 超级魔法绵羊();
		算法.修复效果(()->{
			Buff.延长(statue,Invisibility.class,25*450*2);
		});
		statue.pos = cx + cy * level.width();
		level.mobs.add( statue );
	}
}

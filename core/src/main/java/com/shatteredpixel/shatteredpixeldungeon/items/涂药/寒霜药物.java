

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;

public class 寒霜药物 extends 涂药{

	
	@Override
	public float 触发(Char c,float damage) {
		if(Dungeon.level.water[c.pos]){
			Buff.延长(c,Chill.class,Chill.DURATION);
		}else{
			Buff.延长(c,Chill.class,6f);
		}


		return super.触发( c, damage);
	}
}

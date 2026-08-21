

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;


public class 致盲药物 extends 涂药{
	
	@Override
	public float 触发(Char c,float damage) {
		Buff.施加(c,Blindness.class,Blindness.DURATION);

		return super.触发( c, damage);
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;

public class 激素药物 extends 涂药{
	
	@Override
	public float 触发(Char c,float damage) {
		Buff.延长(c,Cripple.class,Cripple.DURATION/2);
		return super.触发( c, damage);
	}
}

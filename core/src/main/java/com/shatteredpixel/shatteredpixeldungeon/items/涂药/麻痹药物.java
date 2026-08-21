

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;

public class 麻痹药物 extends 涂药{

	@Override
	public float 触发(Char c,float damage) {
		Buff.延长(c,Paralysis.class,5f);

		return super.触发( c, damage);
	}
}

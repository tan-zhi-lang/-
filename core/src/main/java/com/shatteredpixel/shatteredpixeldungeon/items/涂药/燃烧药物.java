

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;

public class 燃烧药物 extends 涂药{


	@Override
	public float 触发(Char c,float damage) {
		Buff.施加(c,燃烧.class).reignite(c);


		return super.触发( c, damage);
	}
}

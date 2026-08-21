

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;

public class 毒液药物 extends 涂药{

	
	@Override
	public float 触发(Char c,float damage) {
		Buff.施加(c,Poison.class).set(3+Dungeon.scalingDepth()/2f);
		return super.触发( c, damage);
	}
}

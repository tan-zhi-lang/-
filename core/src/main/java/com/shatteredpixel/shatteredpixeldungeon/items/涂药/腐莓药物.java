

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;


public class 腐莓药物 extends 涂药{
	@Override
	public float 触发(Char c,float damage) {
			if(c.属性表().contains(Char.Property.BOSS)||c.属性表().contains(Char.Property.MINIBOSS)){
				Buff.施加(c,Corrosion.class).set(5f,Dungeon.scalingDepth()/3);
			}else{
				Buff.施加(c,Corrosion.class).set(10f,Dungeon.scalingDepth());
			}

		return super.触发( c, damage);
	}
	
}

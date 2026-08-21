

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;

public class 治疗药物 extends 涂药{
	
	@Override
	public float 触发(Char c,float damage) {


			//heals 30 hp at base, scaling with enemy HT
		治疗药剂.cure(Dungeon.hero);
		Buff.施加(Dungeon.hero,Healing.class).setHeal((int)(0.5f*Dungeon.hero.最大生命+30),0.25f,0);



		return super.触发( c, damage);
	}
	
}

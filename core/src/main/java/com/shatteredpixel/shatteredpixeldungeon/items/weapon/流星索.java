

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 流星索 extends Weapon{
	
	{
		image = 物品表.BOLAS;
		
		延迟=1.25f;
		伤害=1.35f;
		命中=0.85f;
		tier = 3;
	}
	
	
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null&&defender.第x次防御==1){
			Buff.延长( defender, Cripple.class, Cripple.DURATION/2 );
		}
		return super.攻击时( attacker, defender, damage );
	}
}

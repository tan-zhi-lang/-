

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 权杖 extends Weapon {

	{
		image = 物品表.CUDGEL;
		hitSound = Assets.Sounds.HIT_CRUSH;
		

		tier = 1;
		伤害=0.68f;
		遗产= false;
	}
	
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender.第x次防御==1){
			Buff.延长(defender,Paralysis.class,1);
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public float 投掷攻击时(Char attacker,Char defender,float damage) {
		if(defender.第x次防御==1){
			Buff.延长(defender,Paralysis.class,1);
		}
		return super.投掷攻击时( attacker, defender, damage );
	}
}

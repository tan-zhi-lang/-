

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 臻冰刃 extends Weapon {

	{
		image = 物品表.臻冰刃;
		hitSound = Assets.Sounds.HIT_STAB;
		
		延迟=0.8f;
		伤害=0.8f;
		
		伏击=0.5f;
		tier = 5;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		if(defender.hasbuff(Frost.class)){
			damage+=defender.生命/2;
		}else if(defender.hasbuff(Chill.class)){
			Buff.施加(defender,Frost.class,2);
		}else{
			Buff.施加(defender,Chill.class,2);
		}
			return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		
		if(defender.hasbuff(Frost.class)){
			damage+=defender.生命/2;
		}else if(defender.hasbuff(Chill.class)){
			Buff.施加(defender,Frost.class,2);
		}else{
			Buff.施加(defender,Chill.class,2);
		}
		return super.投掷攻击时( attacker, defender, damage );
	}

}
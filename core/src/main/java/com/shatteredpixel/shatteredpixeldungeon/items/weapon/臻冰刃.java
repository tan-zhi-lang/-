

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.潜行;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 臻冰刃 extends Weapon {

	{
		image = 物品表.臻冰刃;
		hitSound = Assets.Sounds.HIT_STAB;
		
		延迟=1.2f;
		伤害=0.8f;
		技能=new 潜行();
		
		命中=0.85f;
		伏击=0.35f;
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
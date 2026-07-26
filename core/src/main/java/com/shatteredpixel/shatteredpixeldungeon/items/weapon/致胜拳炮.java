

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.拳击手套;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 致胜拳炮 extends Weapon{
	{
		image = 物品表.致胜拳炮;
		hitSound = Assets.Sounds.手枪;
		伤害= 0.7f;
		范围=3;
		特别=true;
		绿色=true;
		tier=5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null){
			attacker.扔出(defender.pos,new 拳击手套(),()->{

			});
			damage*=2;
		}
		return super.攻击时( attacker, defender, damage );
	}

}

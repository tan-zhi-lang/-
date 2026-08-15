

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圣剑 extends Weapon{
	{
		image = 物品表.圣剑;
		hitSound = Assets.Sounds.攻击砍;
		特别=true;
		黄色=true;
		黄光=true;
		伤害=0.8f;
		魔法=0.3f;
		tier=5;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage){

		if(defender!=null&&defender.恶魔亡灵()){
			damage*=1.75f;
		}
		return super.攻击时(attacker,defender,damage);
	}
}

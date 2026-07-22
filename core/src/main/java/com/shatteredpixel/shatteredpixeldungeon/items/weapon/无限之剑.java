

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 无限之剑 extends Weapon{

	{
		image = 物品表.无限之剑;
		hitSound = Assets.Sounds.攻击砍;
		延迟=0.8f;
		技能=new 斩击();
		特别=true;
		tier = 5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {

		damage*=(float)(Math.pow(1.03f, 强化等级()));
		return super.攻击时( attacker, defender, damage );
	}
}

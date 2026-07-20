

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 封印之杖 extends Weapon{
	
	{
		image = 物品表.封印之杖;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier = 5;
		麻痹=15;
		魔法=0.4f;
		伤害= 0.7f;
		特别=true;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null)
			damage+=(defender.最小防御()+defender.最大防御());
		return super.攻击时( attacker, defender, damage );
	}


}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 破甲锥 extends Weapon{

	{
		image = 物品表.破甲锥;
		hitSound = Assets.Sounds.HIT_STAB;
		tier = 3;
	}
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		damage+=defender.最大防御();
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		
		damage+=defender.最大防御();
		return super.投掷攻击时( attacker, defender, damage );
	}

}

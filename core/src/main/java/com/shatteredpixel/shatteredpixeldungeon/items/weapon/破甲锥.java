

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.破击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 破甲锥 extends Weapon{

	{
		image = 物品表.破甲锥;
		hitSound = Assets.Sounds.HIT_STAB;
		技能=new 破击();
		tier = 3;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null
		)damage+=defender.最大防御();
		return super.攻击时( attacker, defender, damage );
	}

}

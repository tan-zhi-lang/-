

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血荆棘 extends Weapon{
	{
		image = 物品表.血荆棘;
		hitSound = Assets.Sounds.攻击刺;
		吸血=0.18f;
		伤害=0.7f;
		特别=true;
		红色=true;
		tier=5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null)attacker.受伤时(attacker.生命(0.05f));
		return super.攻击时( attacker, defender, damage );
	}
}

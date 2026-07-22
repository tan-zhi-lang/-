

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 权杖 extends Weapon {

	{
		image = 物品表.CUDGEL;
		hitSound = Assets.Sounds.攻击锤;
		

		tier = 1;
		专属=true;
		伤害=0.7f;
		魔法=0.15f;

	}

}

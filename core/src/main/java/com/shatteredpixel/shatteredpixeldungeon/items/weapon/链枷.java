

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 链枷 extends Weapon {

	{
		image = 物品表.FLAIL;
		hitSound = Assets.Sounds.HIT_CRUSH;
		

		tier = 3;
		间隔 = 1.25f;
		伤害 = 1.25f;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 短柄镰 extends Weapon {

	{
		image = 物品表.SICKLE;
		hitSound = Assets.Sounds.HIT_SLASH;
		

		tier = 2;
		命中=1.34f;
		间隔=1.5f;
	}
}

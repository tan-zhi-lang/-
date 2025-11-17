

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 弯刀 extends Weapon {

	{
		image = 物品表.SCIMITAR;
		hitSound = Assets.Sounds.HIT_SLASH;
		

		tier = 2;
		命中= 1.25f;
		伤害= 1.25f;
	}
}

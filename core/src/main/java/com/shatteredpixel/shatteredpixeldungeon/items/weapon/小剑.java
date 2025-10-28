

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 小剑 extends Weapon {

	{
		image = 物品表.SHORTSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		tier = 2;
	}
}

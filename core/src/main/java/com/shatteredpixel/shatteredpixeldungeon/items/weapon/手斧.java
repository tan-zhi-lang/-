

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 手斧 extends Weapon {

	{
		image = 物品表.HAND_AXE;
		hitSound = Assets.Sounds.HIT_SLASH;
		

		tier = 1;
		间隔=2;
		伤害=2;
		流血=0.34f;
	}
	

}

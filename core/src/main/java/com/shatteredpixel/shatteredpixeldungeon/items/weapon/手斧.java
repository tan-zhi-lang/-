

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 手斧 extends Weapon {

	{
		image = 物品表.HAND_AXE;
		hitSound = Assets.Sounds.HIT_SLASH;
		

		tier = 2;
		间隔= 1.25f;
		伤害= 1.25f;
	}
	

}

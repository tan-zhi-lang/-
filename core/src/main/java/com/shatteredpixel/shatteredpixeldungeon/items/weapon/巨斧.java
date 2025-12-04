

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨斧 extends Weapon{

	{
		image = 物品表.巨斧;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟=2;
		伤害=2;
		流血=0.25f;
		双手=true;

		tier = 4;
	}

}

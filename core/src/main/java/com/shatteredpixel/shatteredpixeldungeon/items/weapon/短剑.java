

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 短剑 extends Weapon{

	{
		image = 物品表.WORN_SHORTSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;

		tier = 1;
		
		遗产= false;
	}


}

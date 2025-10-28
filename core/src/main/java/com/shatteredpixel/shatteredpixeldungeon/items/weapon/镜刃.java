

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 镜刃 extends Weapon{

	{
		image = 物品表.WORN_SHORTSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		

		tier = 1;
		特别= true;
		遗产= false;
	}
	@Override
	public int 能量() {
		return Math.round(super.能量()*5);
	}

}

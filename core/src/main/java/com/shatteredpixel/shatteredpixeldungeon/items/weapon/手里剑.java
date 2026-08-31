

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 手里剑 extends Weapon{

	{
		image = 物品表.SHURIKEN;
		hitSound = Assets.Sounds.攻击刺;

		延迟=0.8f;
		tier = 1;
	}


}

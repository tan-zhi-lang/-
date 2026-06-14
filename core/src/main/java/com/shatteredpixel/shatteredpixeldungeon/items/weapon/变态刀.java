

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 变态刀 extends Weapon{
	{
		image = 物品表.变态刀;
		hitSound = Assets.Sounds.HIT_STAB;

		延迟=0.8f;
		伤害=0.8f;
		伏击=0.15f;

		特别=true;
		白色=true;
		tier=5;
	}

}

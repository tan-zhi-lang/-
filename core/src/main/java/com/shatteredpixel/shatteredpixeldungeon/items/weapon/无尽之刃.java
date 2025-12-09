

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 无尽之刃 extends Weapon{
	{
		image = 物品表.无尽之刃;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟=1.25f;
		命中=0.8f;
		tier=5;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 蝙蝠棒 extends Weapon{
	{
		image = 物品表.蝙蝠棒;
		hitSound = Assets.Sounds.HIT_CRUSH;
		tier=5;
		延迟=0.5f;
		吸血=0.07f;
	}
}

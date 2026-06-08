

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 回旋镖 extends Weapon{
	
	{
		image = 物品表.BOOMERANG;
		hitSound = Assets.Sounds.HIT_CRUSH;
		延迟=0.8f;
		伤害=0.67f;
		tier = 2;
	}
}

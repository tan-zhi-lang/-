

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 回旋镖 extends Weapon{
	
	{
		image = 物品表.BOOMERANG;
		hitSound = Assets.Sounds.HIT_CRUSH;
		伤害=0.6f;
		tier = 2;
		回旋镖=true;
	}
}

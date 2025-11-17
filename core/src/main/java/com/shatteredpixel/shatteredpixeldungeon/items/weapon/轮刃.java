

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 轮刃 extends Weapon{
	
	{
		image = 物品表.BOOMERANG;
		hitSound = Assets.Sounds.HIT_CRUSH;
		伤害=0.8f;
		流血=0.34f;
		tier = 4;
		回旋镖=true;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 小刺 extends Weapon{

	{
		image = 物品表.THROWING_SPIKE;
		hitSound = Assets.Sounds.HIT_STAB;
		
		遗产= false;
		tier = 1;
	}

}

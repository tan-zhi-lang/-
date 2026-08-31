

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 苦无 extends Weapon{
	
	{
		image = 物品表.KUNAI;
		hitSound = Assets.Sounds.攻击刺;

		延迟=0.8f;
		tier = 3;
	}

}

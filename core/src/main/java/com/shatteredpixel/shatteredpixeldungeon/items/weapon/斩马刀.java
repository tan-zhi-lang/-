

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 斩马刀 extends Weapon {

	{
		image = 物品表.斩马刀;
		hitSound = Assets.Sounds.HIT_SLASH;
		伤害=2;
		延迟=2;
		tier = 3;
	}

}

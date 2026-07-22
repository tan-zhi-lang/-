

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 鹿角刀 extends Weapon{

	{
		image=物品表.鹿角刀;
		hitSound = Assets.Sounds.巨剑;
		伤害=1.2f;
		延迟= 1.2f;


		tier=4;
	}
}

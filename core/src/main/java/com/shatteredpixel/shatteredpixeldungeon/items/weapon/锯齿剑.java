

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 锯齿剑 extends Weapon{
	{
		image = 物品表.锯齿剑;
		hitSound = Assets.Sounds.巨剑;
		流血=0.3f;
		伤害=0.8f;
		特别=true;
		蓝色=true;
		tier=5;
	}

}

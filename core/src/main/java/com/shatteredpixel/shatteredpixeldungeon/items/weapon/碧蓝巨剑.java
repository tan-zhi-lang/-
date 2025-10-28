

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 碧蓝巨剑 extends Weapon{

	{
		image = 物品表.碧蓝巨剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		

		tier = 1;
		
		间隔= 1.5f;
		伤害= 1.8f;
		遗产= false;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 修理扳手 extends Weapon{
	
	{
		image = 物品表.修理扳手;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		吸血=0.03f;
		伤害=0.5f;
		红色 = true;
		特别= true;
		遗产= false;
		
		tier = 1;
	}
}

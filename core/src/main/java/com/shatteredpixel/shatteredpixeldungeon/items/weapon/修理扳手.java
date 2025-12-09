

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 修理扳手 extends Weapon{
	
	{
		image = 物品表.修理扳手;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		吸血=0.035f;
		伤害=0.68f;
		特别= true;
		遗产= false;
		回旋镖=true;
		
		tier = 1;
	}
	
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血砍刀 extends Weapon{
	
	{
		image = 物品表.血砍刀;
		hitSound = Assets.Sounds.HIT_STAB;
		tier = 1;
		
		
		伏击=0.67f;
		流血=0.3f;
		
		特别= true;

		红色 = true;
	}
	
}

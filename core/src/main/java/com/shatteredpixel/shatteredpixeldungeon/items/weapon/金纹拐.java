

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 金纹拐 extends Weapon {

	{
		image = 物品表.金纹拐;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		
		延迟= 1.25f;
		tier = 5;
	}

	@Override
	public int 最大防御(int lvl){
		return 5 + lvl;
	}
	
}

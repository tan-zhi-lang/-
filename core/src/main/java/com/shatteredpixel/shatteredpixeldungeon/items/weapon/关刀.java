

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 关刀 extends Weapon{

	{
		image = 物品表.GLAIVE;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		
		投矛=true;
		延迟=1.5f;
		伤害=1.75f;
		tier = 4;
		范围 = 2;    //extra reach
	}
}

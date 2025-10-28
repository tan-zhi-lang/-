

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 标枪 extends Weapon{

	{
		image = 物品表.JAVELIN;
		hitSound = Assets.Sounds.HIT_STAB;
		
		投矛=true;
		间隔= 1.5f;
		伤害= 1.5f;
		范围 = 2;
		
		tier = 4;
	}
	
}

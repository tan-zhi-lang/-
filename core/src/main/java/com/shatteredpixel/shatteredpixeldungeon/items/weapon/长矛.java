

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长矛 extends Weapon{

	{
		image = 物品表.SPEAR;
		hitSound = Assets.Sounds.HIT_STAB;
		
		
		投矛=true;
		tier = 2;
		间隔= 1.5f;
		伤害= 1.5f;
		范围 = 2;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长剑 extends Weapon{
	
	{
		image = 物品表.LONGSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		
		间隔= 1.34f;
		伤害= 1.34f;
		tier = 3;
	}

}

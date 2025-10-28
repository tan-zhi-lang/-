

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 三叉戟 extends Weapon{
	
	{
		image = 物品表.TRIDENT;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		投矛=true;
		间隔= 1.34f;
		伤害= 1.25f;
		范围 = 2;
		
		tier = 5;
	}
	
}

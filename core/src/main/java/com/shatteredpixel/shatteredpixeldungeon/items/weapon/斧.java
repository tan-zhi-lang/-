

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 斧 extends Weapon{

	{
		image = 物品表.TOMAHAWK;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		
		间隔= 1.25f;
		伤害=1.34f;
		流血=1.34f;
		tier = 4;
	}
	
}

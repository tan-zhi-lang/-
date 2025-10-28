

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 小刀 extends Weapon{
	
	{
		image = 物品表.THROWING_KNIFE;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		伤害=0.8f;
		
		伏击=0.75f;
		遗产= false;
		
		tier = 1;
	}
	
}

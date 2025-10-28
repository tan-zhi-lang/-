

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 吸血刀 extends Weapon{
	
	{
		image = 物品表.吸血刀;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		红色 = true;
		特别= true;
		遗产= false;
		伤害=0.8f;
		吸血=0.07f;
		
		tier = 1;
	}
}

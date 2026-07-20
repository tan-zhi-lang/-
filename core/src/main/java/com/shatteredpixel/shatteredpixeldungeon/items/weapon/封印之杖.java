

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 封印之杖 extends Weapon{
	
	{
		image = 物品表.封印之杖;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier = 5;
		麻痹=15;
		伤害= 0.7f;
		特别=true;
	}

}

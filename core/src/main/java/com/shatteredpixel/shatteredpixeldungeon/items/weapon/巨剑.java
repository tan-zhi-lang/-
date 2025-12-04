

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨剑 extends Weapon{

	{
		image = 物品表.GREATSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		延迟= 1.75f;
		伤害= 1.75f;
		双手=true;
		tier = 4;
	}

}

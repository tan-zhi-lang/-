

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 双剑 extends Weapon{

	{
		image = 物品表.SAI;
		hitSound = Assets.Sounds.HIT_STAB;
		
		
		双手=true;
		tier = 3;
		间隔= 0.5f;
		伤害= 0.64f;
	}

}

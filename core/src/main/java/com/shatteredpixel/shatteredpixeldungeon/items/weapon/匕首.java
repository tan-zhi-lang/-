

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 匕首 extends Weapon{
	
	{
		image = 物品表.小匕;
		hitSound = Assets.Sounds.HIT_STAB;
		
		延迟=0.8f;
		伤害=0.8f;
		
		伏击=0.75f;
		遗产= false;
		
		tier = 1;
	}
	
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 配刺剑 extends Weapon{

	{
		image = 物品表.RAPIER;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier = 1;
		伤害= 0.8f;
		
		遗产= false;
	}
	
	@Override
	public int 最大防御(int lvl){
		return 1 + lvl;
	}


}

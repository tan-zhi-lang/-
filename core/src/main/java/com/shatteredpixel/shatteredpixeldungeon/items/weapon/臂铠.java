

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 臂铠 extends Weapon{

	{
		image = 物品表.臂铠;

		tier = 1;
		间隔= 0.5f;
		伤害= 0.5f;
		
		遗产= false;
	}
	@Override
	public int 最大防御(int lvl){
		return 1 + lvl;
	}
	

}

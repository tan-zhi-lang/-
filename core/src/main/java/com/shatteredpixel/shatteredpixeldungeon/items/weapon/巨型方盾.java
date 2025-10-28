

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨型方盾 extends Weapon {

	{
		image = 物品表.GREATSHIELD;
		
		双手=true;
		间隔= 1.25f;
		伤害= 1.25f;
		tier = 5;
	}
	@Override
	public int 最大防御(int lvl){
		return 5 + lvl;
	}

}

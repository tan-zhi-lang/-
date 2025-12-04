

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨型方盾 extends Weapon {

	{
		image = 物品表.GREATSHIELD;
		
		双手=true;
		伤害= 0.8f;
		延迟= 1.25f;
		tier = 3;
	}
	@Override
	public int 最大防御(int lvl){
		return 4 + lvl;
	}

}

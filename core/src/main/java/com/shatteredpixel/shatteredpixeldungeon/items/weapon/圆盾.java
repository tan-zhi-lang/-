

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圆盾 extends Weapon {

	{
		image = 物品表.ROUND_SHIELD;
		
		双手=true;
		伤害= 0.8f;
		延迟= 1.25f;

		tier = 1;
	}


	@Override
	public int 最大防御(int lvl){
		return 2 + lvl;
	}
	

}
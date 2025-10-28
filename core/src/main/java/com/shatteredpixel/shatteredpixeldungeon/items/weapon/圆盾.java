

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圆盾 extends Weapon {

	{
		image = 物品表.ROUND_SHIELD;
		
		双手=true;
		间隔= 1.25f;
		伤害= 1.25f;

		tier = 3;
	}


	@Override
	public int 最大防御(int lvl){
		return 3 + lvl;
	}
	

}
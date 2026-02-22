

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圆盾 extends Weapon {

	{
		image = 物品表.ROUND_SHIELD;
		
		延迟= 1.25f;
		伤害= 0.85f;
		技能=new 防御姿态();
		tier = 1;
	}


	@Override
	public float 最大防御(int lvl){
		return 1 + lvl;
	}
	

}
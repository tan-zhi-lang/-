

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨型方盾 extends Weapon {

	{
		image = 物品表.GREATSHIELD;
		
		技能=new 防御姿态();
		
		延迟= 1.35f;
		伤害= 0.8f;
		tier = 3;
	}
	@Override
	public float 最大防御(int lvl){
		return 4 + lvl*4;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圆盾 extends Weapon {

	{
		image = 物品表.ROUND_SHIELD;

		技能=new 防御姿态();
		伤害=0.6f;
		tier = 1;
		防御=true;
	}


}
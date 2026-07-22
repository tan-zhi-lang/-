

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圆盾 extends Weapon {

	{
		image = 物品表.ROUND_SHIELD;
		hitSound = Assets.Sounds.盾牌;

		技能=new 防御姿态();
		伤害=0.7f;
		tier = 1;
		具备防御=true;
	}


}
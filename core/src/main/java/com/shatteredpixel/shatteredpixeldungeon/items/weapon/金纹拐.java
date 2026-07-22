

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 金纹拐 extends Weapon {

	{
		image = 物品表.金纹拐;
		hitSound = Assets.Sounds.棍棒;
		
		技能=new 防御姿态();

		特别=true;
		黄色=true;
		伤害= 0.7f;
		tier = 5;
		具备防御=true;
	}

}

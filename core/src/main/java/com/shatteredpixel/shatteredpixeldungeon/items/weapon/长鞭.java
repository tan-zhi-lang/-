

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.群魔乱舞;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长鞭 extends Weapon {

	{
		image = 物品表.WHIP;
		技能=new 群魔乱舞();
		
		
		延迟= 1.35f;
		tier = 3;
		伤害=0.75f;
		连招范围=3;
		范围 = 3;
		命中=0.65f;
	}

}

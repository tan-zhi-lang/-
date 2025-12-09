

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.大杀四方;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长鞭 extends Weapon {

	{
		image = 物品表.WHIP;
		技能=new 大杀四方();
		
		
		延迟= 1.25f;
		tier = 3;
		范围 = 3;    //lots of extra reach
		命中=0.75f;
	}

}

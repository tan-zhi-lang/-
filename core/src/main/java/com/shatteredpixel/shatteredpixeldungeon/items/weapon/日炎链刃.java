

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.大杀四方;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 日炎链刃 extends Weapon {

	{
		image = 物品表.日炎链刃;
		hitSound = Assets.Sounds.日炎链刃;
		
		技能=new 大杀四方();
		
		延迟= 1.5f;
		命中=0.65f;
		tier = 3;
		连招范围=5;
		范围 = 5;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 日炎链刃 extends Weapon {

	{
		image = 物品表.日炎链刃;
		hitSound = Assets.Sounds.日炎链刃;
		
		
		间隔= 1.25f;
		伤害= 1.25f;
		tier = 3;
		范围 = 5;    //lots of extra reach
	}

}

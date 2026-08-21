

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.手枪子弹;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 手枪 extends 枪械{
	
	{
		image = 物品表.手枪;
		
		tier = 1;
		伤害=0.6f;
		射速=5;
		精度=0.7f;
		子弹=new 手枪子弹();
		image2 = 物品表.手枪子弹;
		hitSound2 = Assets.Sounds.手枪;
		item_Miss2 = Assets.Sounds.手枪;
	}
	@Override
	public int initialCharges() {
		return 7;
	}
}

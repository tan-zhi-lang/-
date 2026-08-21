

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.火炮子弹;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 火炮 extends 枪械{
	
	{
		image = 物品表.火炮;
		范围=2;
		tier = 5;
		伤害=0.6f;
		枪伤=6.6f;
		射速=2;
		精度=0.8f;
		爆炸效果=true;
		子弹=new 火炮子弹();
		image2 = 物品表.火炮子弹;
		hitSound2 = Assets.Sounds.火炮;
		item_Miss2 = Assets.Sounds.火炮;
	}
	@Override
	public int initialCharges() {
		return 1;
	}
}

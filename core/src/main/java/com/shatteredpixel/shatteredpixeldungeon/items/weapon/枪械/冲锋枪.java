

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.冲锋枪子弹;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 冲锋枪 extends 枪械{
	
	{
		image = 物品表.冲锋枪;
		tier = 2;
		发射次数 = 3;
		枪伤=1.5f;
		射速=8;
		精度=0.7f;
		子弹=new 冲锋枪子弹();
		image2 = 物品表.冲锋枪子弹;
		hitSound2 = Assets.Sounds.冲锋枪;
		item_Miss2 = Assets.Sounds.冲锋枪;
	}
	@Override
	public int initialCharges() {
		return 30;
	}
	@Override
	protected int chargesPerCast() {
		return 3;
	}
}

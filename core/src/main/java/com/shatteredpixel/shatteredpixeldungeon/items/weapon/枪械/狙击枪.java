

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.狙击枪子弹;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 狙击枪 extends 枪械{
	public static final String AC_SHOOT		= "SHOOT";
	public static final String AC_换弹		= "换弹";
	
	{
		image = 物品表.狙击枪;
		范围=2;
		tier = 4;
		伤害=0.6f;
		投掷=1.75f;
		破甲弹=true;
		子弹=new 狙击枪子弹();
		射速=2;
		image2 = 物品表.狙击枪子弹;
		hitSound2 = Assets.Sounds.狙击枪;
		item_Miss2 = Assets.Sounds.狙击枪;
	}
	@Override
	public int initialCharges() {
		return 7;
	}
	@Override
	protected int chargesPerCast() {
		return 1;
	}
}

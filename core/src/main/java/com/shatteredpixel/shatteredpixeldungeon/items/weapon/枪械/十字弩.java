

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.十字弩飞镖;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 十字弩 extends 枪械{
	
	{
		image = 物品表.十字弩;
		
		tier = 1;
		伤害=0.6f;
		枪伤=1.25f;
		射速=2;
		子弹 = new 十字弩飞镖();
		image2 = 物品表.DART;
		hitSound2 = Assets.Sounds.攻击箭;
		item_Miss2 = Assets.Sounds.攻击箭;
		掉落子弹=true;
	}
	@Override
	public int initialCharges() {
		return 1;
	}
}

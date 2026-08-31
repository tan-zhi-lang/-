

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.石子;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 弹弓 extends 枪械{
	
	{
		image = 物品表.弹弓;
		
		tier = 1;
		伤害=0.6f;
		枪伤=1f;
		子弹 = new 石子();
		image2 = 物品表.石子;
		hitSound2 = Assets.Sounds.卡牌;
		item_Miss2 = Assets.Sounds.卡牌;
		换弹声音 = Assets.Sounds.拉弓;
		掉落子弹=true;
		箭矢发射=true;
		开火效果=false;
	}
	@Override
	public int initialCharges() {
		return 1;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.箭矢;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 短弓 extends 枪械{
	
	{
		image = 物品表.短弓;
		
		tier = 1;
		伤害=0.6f;
		枪伤=1.35f;
		射速=1;
		子弹 = new 箭矢();
		image2 = 物品表.箭矢;
		hitSound2 = Assets.Sounds.攻击箭;
		item_Miss2 = Assets.Sounds.攻击箭;
		掉落子弹=true;
		箭矢发射=true;
		开火效果=false;
	}
	@Override
	public int initialCharges() {
		return 1;
	}
}

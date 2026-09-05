

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.箭矢;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 十字弩 extends 枪械{
	
	{
		image = 物品表.十字弩;
		特别=true;
		专属=true;
		tier = 1;
		伤害=0.6f;
		枪伤= 1.75f;
		射速=2;
		子弹 = new 箭矢();
		image2 = 物品表.箭矢;
		hitSound2 = Assets.Sounds.攻击弩;
		item_Miss2 = Assets.Sounds.攻击弩;
		换弹声音 = Assets.Sounds.拉弓;
		掉落子弹=true;
		箭矢发射=true;
		开火效果=false;
	}
	@Override
	public int initialCharges() {
		return 1;
	}
	@Override
	public float 换弹回合() {
		return 0;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.毒气药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.自然箭矢;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 炼金动力十字弩 extends 枪械{
	
	{
		image = 物品表.炼金动力十字弩;
		
		tier = 3;
		伤害=0.6f;
		射速=2;
		中毒=0.15f;
		子弹 = new 自然箭矢();
		image2 = 物品表.自然箭矢;
		hitSound2 = Assets.Sounds.攻击箭;
		item_Miss2 = Assets.Sounds.攻击箭;

		箭矢发射=true;
		开火效果=false;
	}
	@Override
	public int initialCharges() {
		return 3;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{十字弩.class,
					毒气药剂.class,};
			inQuantity = new int[]{1,1,};

			cost = 9;

			output = 炼金动力十字弩.class;
			outQuantity = 1;
		}

	}
}

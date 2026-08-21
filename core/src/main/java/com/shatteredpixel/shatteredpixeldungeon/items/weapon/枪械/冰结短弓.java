

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.冰霜箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 冰结短弓 extends 枪械{
	
	{
		image = 物品表.冰结短弓;
		
		tier = 5;
		伤害=0.6f;
		枪伤=1.25f;
		射速=1;
		冻结=0.15f;
		子弹 = new 冰霜箭矢();
		image2 = 物品表.冰霜箭矢;
		hitSound2 = Assets.Sounds.攻击灵箭;
		item_Miss2 = Assets.Sounds.攻击灵箭;

		箭矢发射=true;
		无限子弹=true;
		开火效果=false;
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{灵能短弓.class,
					冰霜药剂.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 冰结短弓.class;
			outQuantity = 1;
		}

	}
}

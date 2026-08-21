

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.灵能箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 灵能短弓 extends 枪械{
	
	{
		image = 物品表.灵能短弓;
		
		tier = 3;
		伤害=0.6f;
		枪伤=1.25f;
		射速=1;
		子弹 = new 灵能箭矢();
		image2 = 物品表.冰霜箭矢;
		hitSound2 = Assets.Sounds.攻击灵箭;
		item_Miss2 = Assets.Sounds.攻击灵箭;

		箭矢发射=true;
		开火效果=false;
		无限子弹=true;
	}


	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{短弓.class,
					器灵.class,};
			inQuantity = new int[]{1,1,};

			cost = 9;

			output = 灵能短弓.class;
			outQuantity = 1;
		}

	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.灵能箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 灵能短弓 extends 短弓{
	
	{
		image = 物品表.灵能短弓;
		
		tier = 3;
		子弹 = new 灵能箭矢();
		image2 = 物品表.冰霜箭矢;
		hitSound2 = Assets.Sounds.攻击灵箭;
		item_Miss2 = Assets.Sounds.攻击灵箭;

		无限子弹=true;
		掉落子弹=false;
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

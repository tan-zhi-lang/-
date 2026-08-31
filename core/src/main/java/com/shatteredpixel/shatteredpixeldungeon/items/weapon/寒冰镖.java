

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 寒冰镖 extends Weapon{

	{
		image = 物品表.寒冰镖;
		hitSound = Assets.Sounds.攻击刺;
		circlingBack=true;
		延迟=0.8f;

		冻结=0.15f;
		tier = 5;
		特别=true;
		黄色=true;
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{手里剑.class,
					冰霜药剂.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 寒冰镖.class;
			outQuantity = 1;
		}

	}
}

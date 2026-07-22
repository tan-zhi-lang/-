

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.刺退;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 关刀 extends Weapon{

	{
		image = 物品表.GLAIVE;
		hitSound = Assets.Sounds.长枪;
		
		技能=new 刺退();
		延迟=2;
		tier = 4;
//		连招范围=2;
		范围 = 2;
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{长矛.class,
					LiquidMetal.class,};
			inQuantity = new int[]{1,1,};

			cost = 12;

			output = 关刀.class;
			outQuantity = 1;
		}

	}
}

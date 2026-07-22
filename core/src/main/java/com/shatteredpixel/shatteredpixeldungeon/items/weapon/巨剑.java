

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨剑 extends Weapon{

	{
		image = 物品表.GREATSWORD;
		hitSound = Assets.Sounds.巨剑;
		
		技能=new 斩击();
		
		tier = 4;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{长剑.class,
					LiquidMetal.class,};
			inQuantity = new int[]{1,1,};

			cost = 12;

			output = 巨剑.class;
			outQuantity = 1;
		}

	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.背刺;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长匕首 extends Weapon{

	{
		image = 物品表.DIRK;
		hitSound = Assets.Sounds.攻击刺;

		延迟=0.8f;
		
		技能=new 背刺();

		tier = 2;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{匕首.class,
					LiquidMetal.class,};
			inQuantity = new int[]{1,1,};

			cost = 6;

			output = 长匕首.class;
			outQuantity = 1;
		}

	}
}

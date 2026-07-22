

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长剑 extends Weapon{
	
	{
		image = 物品表.LONGSWORD;
		hitSound = Assets.Sounds.攻击砍;
		
		技能=new 斩击();

		tier = 3;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{单手剑.class,
					LiquidMetal.class,};
			inQuantity = new int[]{1,1,};

			cost = 9;

			output = 长剑.class;
			outQuantity = 1;
		}

	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 单手剑 extends Weapon{
	
	{
		image = 物品表.SWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 斩击();
		tier = 2;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{短剑.class,
					LiquidMetal.class,};
			inQuantity = new int[]{1,1,};

			cost = 6;

			output = 单手剑.class;
			outQuantity = 1;
		}

	}
}

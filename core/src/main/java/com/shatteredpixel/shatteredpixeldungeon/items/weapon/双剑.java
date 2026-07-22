

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.剑舞;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 双剑 extends Weapon{

	{
		image = 物品表.SAI;
		hitSound = Assets.Sounds.攻击砍;
		
		
		技能=new 剑舞();
		
		tier = 3;
		延迟= 0.5f;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{双刃.class,
					LiquidMetal.class,};
			inQuantity = new int[]{1,1,};

			cost = 9;

			output = 双剑.class;
			outQuantity = 1;
		}

	}
}

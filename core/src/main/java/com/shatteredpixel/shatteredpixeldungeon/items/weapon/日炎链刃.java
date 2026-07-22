

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 日炎链刃 extends Weapon {

	{
		image = 物品表.日炎链刃;
		hitSound = Assets.Sounds.日炎链刃;
		
		伤害=0.8f;

		tier = 5;
//		连招范围=5;
		范围 = 5;
		特别=true;
		橙色=true;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{链刃.class,
					Bomb.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 日炎链刃.class;
			outQuantity = 1;
		}

	}
}

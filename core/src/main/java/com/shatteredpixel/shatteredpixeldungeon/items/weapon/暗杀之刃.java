

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.背刺;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 暗杀之刃 extends Weapon {

	{
		image = 物品表.ASSASSINS_BLADE;
		hitSound = Assets.Sounds.HIT_STAB;

		伤害=0.8f;
		
		特别=true;
		靛色=true;
		技能=new 背刺();

		tier = 5;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{匕首.class,长匕首.class,};
			inQuantity = new int[]{1,1,};

			cost = 15;

			output = 暗杀之刃.class;
			outQuantity = 1;
		}

	}

}


package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.潜力药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 进力药剂 extends 用品 {
	
	
	{
		image = 物品表.进力药剂;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{潜力药剂.class,
					精纯药液.class,};
			inQuantity = new int[]{1,1,};

			cost = 8;

			output = 进力药剂.class;
			outQuantity = 1;
		}

	}
	@Override
	public void 使用(Hero hero){
		hero.力量+=2.5f;
		super.使用(hero);
	}

}

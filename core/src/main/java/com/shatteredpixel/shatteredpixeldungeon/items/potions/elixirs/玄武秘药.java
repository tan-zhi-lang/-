

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.毒气药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.麻痹药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 玄武秘药 extends Elixir {

	{
		image = 物品表.玄武秘药;
		icon = 物品表.Icons.玄武;
		
		特别= true;

		talentFactor = 2f;
	}
	@Override
	public int 金币() {
		return quantity * 120;
	}

	@Override
	public void apply( Hero hero ) {
		hero.护甲成长+=5;
		hero.防御成长+=2;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{毒气药剂.class,
					麻痹药剂.class,};
			inQuantity = new int[]{1,1,};

			cost = 15;
			
			output = 玄武秘药.class;
			outQuantity = 1;
		}
		
	}
	
}

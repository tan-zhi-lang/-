

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.潜力药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.精纯药液;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 根骨秘药 extends Elixir {

	{
		image = 物品表.ELIXIR_MIGHT;
		icon = 物品表.Icons.根骨;
		
		特别= true;

		talentFactor = 2f;
	}
	
	@Override
	public void apply( Hero hero ) {
		鉴定();
		hero.主属性(1);
		hero.根骨+=4;

//		Buff.施加(hero, HTBoost.class).reset();
//		HTBoost boost = Buff.施加(hero, HTBoost.class);
//		boost.reset();
		
		

		Badges.validateStrengthAttained();
		Badges.validateDuelistUnlock();
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{潜力药剂.class,
					精纯药液.class};
			inQuantity = new int[]{1,1};
			
			cost = 10;
			
			output = 根骨秘药.class;
			outQuantity = 1;
		}
		
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.潜力药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血怒秘药 extends Elixir {

	{
		image = 物品表.血怒秘药;
		icon = 物品表.Icons.血怒;
		
		特别= true;

		talentFactor = 2f;
	}
	@Override
	public int 金币() {
		return quantity * 120;
	}

	@Override
	public void apply( Hero hero ) {
		hero.吸血成长+=0.01f;
		hero.暴伤成长+=0.15f;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{潜力药剂.class,
					治疗药剂.class,};
			inQuantity = new int[]{1,1,};

			cost = 15;
			
			output = 血怒秘药.class;
			outQuantity = 1;
		}
		
	}
	
}

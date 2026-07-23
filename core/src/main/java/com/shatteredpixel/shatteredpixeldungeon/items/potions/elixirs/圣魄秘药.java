

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.净化药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圣魄秘药 extends Elixir {

	{
		image = 物品表.圣魄秘药;
		icon = 物品表.Icons.圣魄;
		
		特别= true;

		talentFactor = 2f;
	}
	@Override
	public int 金币() {
		return quantity * 120;
	}

	@Override
	public void apply( Hero hero ) {
		hero.火把神+=1;
		hero.魔抗成长+=2f;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{净化药剂.class,
			经验药剂.class,};
			inQuantity = new int[]{1,1,};

			cost = 15;
			
			output = 圣魄秘药.class;
			outQuantity = 1;
		}
		
	}
	
}

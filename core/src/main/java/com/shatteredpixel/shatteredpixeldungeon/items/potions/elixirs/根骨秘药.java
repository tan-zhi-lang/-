

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.力量药剂;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

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

		hero.根骨+=5;
		
//		Buff.施加(hero, HTBoost.class).reset();
//		HTBoost boost = Buff.施加(hero, HTBoost.class);
//		boost.reset();
		
		hero.更新属性();

		Badges.validateStrengthAttained();
		Badges.validateDuelistUnlock();
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{力量药剂.class};
			inQuantity = new int[]{1};
			
			cost = 16;
			
			output = 根骨秘药.class;
			outQuantity = 1;
		}
		
	}

}

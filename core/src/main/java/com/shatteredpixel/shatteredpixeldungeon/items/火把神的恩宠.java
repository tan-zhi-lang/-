

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 火把神的恩宠 extends 用品 {
	
	
	{
		image = 物品表.火把神的恩宠;
	}
	
	@Override
	public void 使用(Hero hero){
		hero.火把神++;
		super.使用(hero);
	}
	public static class R extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		{
			inputs =  new Class[]{Torch.class};
			inQuantity = new int[]{5,};

			cost = 5;

			output = 火把神的恩宠.class;
		}
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 蓝蘑菇 extends Food {

	{
		image = 物品表.蓝蘑菇;
		energy = Hunger.HUNGRY/3f*2; //100 food value
		

	}

	@Override
	public float eatingTime(){
		return super.eatingTime()-1;
	}

	@Override
	protected void satisfy(Hero hero) {
		hero.回血(20);
		super.satisfy(hero);
	}

	@Override
	public int 金币() {
		return 0;
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{蓝蘑菇.class};
			inQuantity = new int[]{1,};

			cost = 1;

			output = 红蘑菇.class;
			outQuantity = 1;
		}

	}
}

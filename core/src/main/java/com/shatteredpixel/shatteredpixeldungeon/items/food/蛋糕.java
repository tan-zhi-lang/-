

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 蛋糕 extends Food {

	{
		image = 物品表.蛋糕;
		energy = Hunger.STARVING; //100 food value
		

	}

	@Override
	protected void satisfy(Hero hero) {
		if(hero.符文("当心小蛋糕")){
			hero.回已损失血(0.2f);
		}
		super.satisfy(hero);
	}

	@Override
	public int 金币() {
		return 0;
	}
}

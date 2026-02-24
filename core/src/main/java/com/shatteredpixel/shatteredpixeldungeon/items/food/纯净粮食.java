

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 纯净粮食 extends Food {

	{
		image = 物品表.纯净粮食;
		energy = Hunger.HUNGRY/3f*2; //100 food value
		

	}

	@Override
	public float eatingTime(){
		return super.eatingTime()-1;
	}

	@Override
	protected void satisfy(Hero hero) {
		
		super.satisfy(hero);
	}

	@Override
	public int 金币() {
		return 0;
	}
}

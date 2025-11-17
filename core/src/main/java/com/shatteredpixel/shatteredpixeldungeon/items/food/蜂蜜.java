

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 蜂蜜 extends Food {

	{
		image = 物品表.蜂蜜;
		energy = Hunger.HUNGRY/3f*2; //100 food value
		
		遗产= false;
	}

	@Override
	public float eatingTime(){
		return super.eatingTime()-1;
	}

	@Override
	protected void satisfy(Hero hero) {
		Buff.施加(hero,Healing.class).setHeal(20,0,1);
		super.satisfy(hero);
	}

	@Override
	public int 金币() {
		return 20 * quantity;
	}
}

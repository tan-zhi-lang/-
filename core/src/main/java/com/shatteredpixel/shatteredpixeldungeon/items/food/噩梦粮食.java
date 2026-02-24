

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 噩梦粮食 extends Food {

	{
		image = 物品表.噩梦粮食;
		energy = Hunger.HUNGRY/3f*2; //100 food value
		黑色=true;

	}

	@Override
	public float eatingTime(){
		return super.eatingTime()-1;
	}

	@Override
	protected void satisfy(Hero hero) {
		Buff.施加(hero,Weakness.class,10);
		Buff.施加(hero,Vulnerable.class,10);
		Buff.施加(hero,Slow.class,10);
		Buff.施加(hero,Hex.class,10);
		super.satisfy(hero);
	}

	@Override
	public int 金币() {
		return 0;
	}
}

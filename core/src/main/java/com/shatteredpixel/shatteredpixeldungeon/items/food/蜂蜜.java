

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 蜂蜜 extends Food {

	{
		image = 物品表.蜂蜜;
		energy = Hunger.STARVING/4f*(1+(Dungeon.hero()?Dungeon.hero.天赋点数(Talent.蜂蛊技术,0.25f):0)); //100 food value
		

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
		return 30 * quantity;
	}
}

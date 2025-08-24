

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class SmallRation extends Food {

	{
		image = 物品表.OVERPRICED;
		energy = Hunger.HUNGRY/2f;
	}
	
	@Override
	public int 金币() {
		return super.金币()/2;
	}
}

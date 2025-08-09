

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ChargrilledMeat extends Food {

	{
		image = 物品表.STEAK;
		energy = Hunger.HUNGRY/2f;
	}
	
	@Override
	public int value() {
		return 8 * quantity;
	}
	
	public static Food cook( int quantity ) {
		ChargrilledMeat result = new ChargrilledMeat();
		result.quantity = quantity;
		return result;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 勇装 extends Armor {

	{
		image = 物品表.勇装;
	}

	public 勇装(){
		super(1);
	}
	
	@Override
	public int 力量(int lvl) {
		int req = 力量(tier, lvl)-2;
		if (masteryPotionBonus){
			req -= 2;
		}
		return req;
	}
}
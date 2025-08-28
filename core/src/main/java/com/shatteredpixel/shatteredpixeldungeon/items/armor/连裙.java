

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 连裙 extends Armor {

	{
		image = 物品表.连裙;
	}

	public 连裙(){
		super(1);
	}
	@Override
	public int 力量(int lvl) {
		int req = 力量(tier, lvl)-1;
		if (masteryPotionBonus){
			req -= 2;
		}
		return req;
	}
}
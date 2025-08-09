

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 铠甲 extends Armor {

	{
		image = 物品表.ARMOR_WARRIOR;
	}
	public 铠甲(){
        super(2);
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


package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 风斩电刺 extends 技能 {
	{
		image=物品表.WEAPON_HOLDER;
	}

	@Override
	public int initialCharges() {
		return 2;
	}
	@Override
	public int chargesPerCast() {
		return 2;
	}
}

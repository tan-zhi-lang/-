

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 三重爪击 extends 技能 {
	{
		image=物品表.WEAPON_HOLDER;
	}

	@Override
	public int initialCharges() {
		return 3;
	}
	@Override
	public int chargesPerCast() {
		return 3;
	}
}

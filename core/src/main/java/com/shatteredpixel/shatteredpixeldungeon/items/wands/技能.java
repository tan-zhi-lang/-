

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;

public class 技能 extends Wand {
	{
		不能丢扔=true;
		usesTargeting=false;
		curCharges=0;
	}
	int 充能=0;
	@Override
	public int initialCharges() {
		return 1;
	}
	@Override
	public int chargesPerCast() {
		return 1;
	}

	@Override
	public void onZap(Ballistica attack){

	}
}

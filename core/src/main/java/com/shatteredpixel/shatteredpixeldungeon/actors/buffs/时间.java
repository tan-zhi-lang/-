package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

public class 时间 extends FlavourBuff{
	
	@Override
	public boolean act() {
		spend( TICK );
		return true;
	}
}

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class 冷却时间 extends FlavourBuff{
	public int icon() { return BuffIndicator.TIME; }
	@Override
	public String desc() {
		return Messages.get(this,"desc",name(),dispTurns());
	}
}
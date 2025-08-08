

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class Drowsy extends FlavourBuff {

	public static final float DURATION = 5f;

	{
		type = buffType.NEUTRAL;
		announced = true;
	}

	@Override
	public int icon() {
		return BuffIndicator.DROWSY;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	public boolean attachTo(Char target ) {
		if (!target.isImmune(Sleep.class) && super.attachTo(target)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean act(){
		Buff.施加(target, MagicalSleep.class);

		return super.act();
	}

}

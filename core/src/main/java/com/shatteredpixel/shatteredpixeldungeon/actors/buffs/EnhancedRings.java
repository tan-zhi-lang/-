

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class EnhancedRings extends FlavourBuff {

	{
		type = Buff.buffType.POSITIVE;
	}

	@Override
	public boolean attachTo(Char target) {
		if (super.attachTo(target)){
//			if (target == Dungeon.hero) ((Hero) target).
			return true;
		}
		return false;
	}

	@Override
	public void detach() {
		super.detach();
//		if (target == Dungeon.hero) ((Hero) target).
	}

	@Override
	public int icon() {
		return BuffIndicator.UPGRADE;
	}

	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(0, 1, 0);
	}

	@Override
	public float iconFadePercent() {
		float max = 2;
		return Math.max(0, (max-visualcooldown()) / max);
	}

}

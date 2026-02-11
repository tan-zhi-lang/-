package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.GameMath;

public class 组间休息 extends FlavourBuff{
	@Override
	public boolean act() {
		spend( TICK );
		return true;
	}
	public int icon() { return BuffIndicator.TIME; }
	public void tintIcon(Image icon) { icon.hardlight(0f,0.35f,0.15f); }
	public float iconFadePercent() { return GameMath.之内(0,visualcooldown()/1f,1); }
}

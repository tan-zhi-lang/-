package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;

public class 冷却时间 extends FlavourBuff{

	protected float 冷却;

	public void set( float 冷却 ) {
		this.冷却=冷却;
	}
	private static final String 冷却x	= "冷却";

	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle( bundle );
		bundle.put( 冷却x, 冷却 );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		冷却 = bundle.getFloat( 冷却x );
	}
	public int icon() { return BuffIndicator.TIME; }
	public float iconFadePercent() { return GameMath.之内(0,(冷却-visualcooldown())/(冷却),1); }
}
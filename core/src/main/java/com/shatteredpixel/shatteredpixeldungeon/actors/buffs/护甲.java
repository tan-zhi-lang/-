

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

public class 护甲 extends Buff {
	
	{
		type = buffType.POSITIVE;
	}

	private int level = 0;
	private int interval = 1;
	
	@Override
	public boolean act() {
		if (target.isAlive()) {

			spend( interval );
			if (--level <= 0) {
				detach();
			}
			
		} else {
			
			detach();
			
		}
		
		return true;
	}
	
	public int level() {
		return level;
	}
	
	public void set( int value, int time ) {
		if (level <= value) {
			level = value;
			interval = time;
			spend(time - cooldown() - 1);
		}
	}

	public void delay( float value ){
		spend(value);
	}
	
	@Override
	public int icon() {
		return BuffIndicator.SEAL_SHIELD;
	}

	@Override
	public float iconFadePercent() {
		if (target instanceof Hero){
			float max = ((Hero) target).等级 *((Hero) target).天赋点数(Talent.BARKSKIN,0.5f);
			max = Math.max(max, 2+((Hero) target).等级 /3);
			return Math.max(0, (max-level)/max);
		}
		return 0;
	}

	@Override
	public String iconTextDisplay() {
		return Integer.toString(level);
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", level, dispTurns(visualcooldown()));
	}
	
	private static final String LEVEL	    = "level";
	private static final String INTERVAL    = "interval";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( INTERVAL, interval );
		bundle.put( LEVEL, level );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		interval = bundle.getInt( INTERVAL );
		level = bundle.getInt( LEVEL );
	}

	//These two methods allow for multiple instances of barkskin to stack in terms of duration
	// but only the stronger bonus is applied

	public static int currentLevel(Char ch ){
		int level = 0;
		for (护甲 b : ch.buffs(护甲.class)){
			level = Math.max(level, b.level);
		}
		return level;
	}

	//reset if a matching buff exists, otherwise append
	public static void conditionallyAppend(Char ch, int level, int interval){
		for (护甲 b : ch.buffs(护甲.class)){
			if (b.interval == interval){
				b.set(level, interval);
				return;
			}
		}
		Buff.新增(ch, 护甲.class).set(level,interval);
	}
}



package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.魔法箭矢;
import com.watabou.utils.Bundle;

public class 伤害 extends Buff{
	
	public float level;
	private static final String LEVEL	= "level";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( LEVEL, level );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		level = bundle.getFloat( LEVEL );
	}

	@Override
	public boolean act() {
		if (target.isAlive()) {
			if (level > 0) {
				target.受伤时(level,魔法箭矢.class);
				level=0;
				spend( 0 );
			} else {
				detach();
			}
			
		} else {
			
			detach();
			
		}
		
		return true;
	}
}

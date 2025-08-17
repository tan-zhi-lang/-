

package com.shatteredpixel.shatteredpixeldungeon.levels.traps;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Wound;

public class GrippingTrap extends Trap {

	{
		color = GREY;
		shape = DOTS;

		disarmedByActivation = false;
		avoidsHallways = true;
	}

	@Override
	public void activate() {

		Char c = Actor.findChar( pos );

		if (c != null && !c.flying) {
			if (c instanceof Mob) {
				Buff.延长(c, Trap.HazardAssistTracker.class, HazardAssistTracker.DURATION);
			}
			int damage = Math.max( 0,  (2 + scalingDepth()/2) - c.防御()/2 );
			Buff.施加( c, 流血.class ).set( damage );
			Buff.延长( c, Cripple.class, Cripple.DURATION);
			Wound.hit( c );
		} else {
			Wound.hit( pos );
		}

	}
}

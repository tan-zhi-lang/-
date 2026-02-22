

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.PointF;

public class 死舞 extends Buff {
	
	{
		type = buffType.NEGATIVE;
	}
	
	protected float damage = 0;
	
	private static final String DAMAGE	= "damage";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( DAMAGE, damage );
		
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		damage = bundle.getFloat( DAMAGE );
	}
	
	public void extend( float damage ) {
		if (this.damage == 0){
			//wait 1 turn before damaging if this is freshly applied
			postpone(TICK);
		}
		this.damage += damage;
	}
	
	@Override
	public int icon() {
		return BuffIndicator.BLEEDING;
	}
	
	@Override
	public String iconTextDisplay() {
		return Float.toString(damage);
	}
	
	@Override
	public boolean act() {
		if (target.isAlive()) {

			float damageThisTick = Math.max(1, damage/3);

			target.受伤时( damageThisTick, this );
			if (target.sprite.visible) {
				Splash.at(target.sprite.center(),-PointF.PI/2,PointF.PI/6,
						  target.sprite.blood(),Math.min( 10 * damageThisTick / target.最大生命, 10 ));
			}
			if (target==Dungeon.hero&&!target.isAlive()) {
				
				Badges.validateDeathFromFriendlyMagic();
				
				Dungeon.fail( this );
				GLog.n(Messages.get(this,"ondeath"));
			}
			spend( TICK );
			
			damage -= damageThisTick;
			if (damage <= 0.01f) {
				detach();
			}
			
		} else {
			
			detach();
			
		}
		
		return true;
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc", String.format("%.2f",damage));
	}
}

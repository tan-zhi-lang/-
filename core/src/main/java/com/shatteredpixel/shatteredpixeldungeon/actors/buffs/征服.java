

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

public class 征服 extends Buff {
	
	{
		type = buffType.POSITIVE;

		//acts before the hero
		actPriority = HERO_PRIO+1;
	}
	
	public int 层数= 0;
	
	public float iconFadePercent() { return Math.max(0, 1f - ((float)层数/ 12)); }
	@Override
	public boolean act() {
		if(target instanceof Hero hero){
			if(hero.战斗状态判定<=0){
				层数= Math.max(层数-2,0);
			}
		}
		spend(TICK);
		return true;
	}
	
	public void 叠层(){
		层数= Math.min(层数+2,12);
	}
	
	public boolean 满层(){
		return 层数>=12;
	}
	
	@Override
	public int icon() {
		return BuffIndicator.TIME;
	}
	
	@Override
	public String desc() {
		return Messages.get(this,"desc",层数);
	}
	
	private static final String 层数x=        "层数";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(层数x,层数);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		层数= bundle.getInt(层数x);
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class RatSkull extends Trinket {

	{
		image = 物品表.RAT_SKULL;
	}

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (isIdentified()){
			return Messages.get(this, "stats_desc", (int)(exoticChanceMultiplier(强化等级())));
		} else {
			return Messages.get(this, "typical_stats_desc", (int)(exoticChanceMultiplier(0)));
		}
	}

	public static float exoticChanceMultiplier(){
		return exoticChanceMultiplier(trinketLevel(RatSkull.class));
	}

	public static float exoticChanceMultiplier( int level ){
		if (level == -1){
			return 1f;
		} else {
			return 2f + 1f*level;
		}
	}

}

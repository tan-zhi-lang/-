

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ExoticCrystals extends Trinket {

	{
		image = 物品表.EXOTIC_CRYSTALS;
	}

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (已鉴定()){
			return Messages.get(this, "stats_desc", Messages.decimalFormat("#.##", 100*consumableExoticChance(强化等级())));
		} else {
			return Messages.get(this, "typical_stats_desc", Messages.decimalFormat("#.##", 100*consumableExoticChance(0)));
		}
	}

	public static float consumableExoticChance(){
		return consumableExoticChance(trinketLevel(ExoticCrystals.class));
	}

	public static float consumableExoticChance( int level ){
		if (level == -1){
			return 0f;
		} else {
			return 0.125f + 0.125f*level;
		}
	}

}

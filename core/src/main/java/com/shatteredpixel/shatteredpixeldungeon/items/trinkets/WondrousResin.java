

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class WondrousResin extends Trinket {

	{
		image = 物品表.WONDROUS_RESIN;
	}

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 10(16) -> 15(31) -> 20(51)
		return 10+5* 等级();
	}

	@Override
	public String statsDesc() {
		if (已鉴定()){
			return Messages.get(this, "stats_desc",
					Messages.decimalFormat("#.##", 100*positiveCurseEffectChance(强化等级())),
					Messages.decimalFormat("#.##", 100*extraCurseEffectChance(强化等级())));
		} else {
			return Messages.get(this, "stats_desc",
					Messages.decimalFormat("#.##", 100*positiveCurseEffectChance(0)),
					Messages.decimalFormat("#.##", 100*extraCurseEffectChance(0)));
		}
	}

	//used when bonus curse effects are being created
	public static boolean forcePositive = false;

	public static float positiveCurseEffectChance(){
		if (forcePositive){
			return 1;
		}
		return positiveCurseEffectChance( trinketLevel(WondrousResin.class) );
	}

	public static float positiveCurseEffectChance(int level ){
		if (level >= 0){
			return 0.25f + 0.25f * level;
		} else {
			return 0;
		}
	}

	public static float extraCurseEffectChance(){
		return extraCurseEffectChance( trinketLevel(WondrousResin.class) );
	}

	public static float extraCurseEffectChance( int level ){
		if (level >= 0){
			return 0.125f + 0.125f * level;
		} else {
			return 0;
		}
	}

}

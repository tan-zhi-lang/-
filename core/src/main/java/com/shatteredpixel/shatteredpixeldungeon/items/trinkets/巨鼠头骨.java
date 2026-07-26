

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨鼠头骨 extends Trinket {

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
		if (已鉴定()){
			return Messages.get(this, "stats_desc", exoticChanceMultiplier(等级()), 弱(等级()));
		} else {
			return Messages.get(this, "stats_desc", exoticChanceMultiplier(0), 弱(0));
		}
	}

	public static float exoticChanceMultiplier(){
		return exoticChanceMultiplier(trinketLevel(巨鼠头骨.class));
	}

	public static float exoticChanceMultiplier( int level ){
		if (level == -1){
			return 1f;
		} else {
			return 2.5f + 1.5f*level;
		}
	}
	public static float 弱(){
		return exoticChanceMultiplier(trinketLevel(巨鼠头骨.class));
	}

	public static float 弱( int level ){
		if (level == -1){
			return 1f;
		} else {
			return 0.75f - 0.25f*level;
		}
	}

}

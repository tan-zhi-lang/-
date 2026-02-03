

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class ThirteenLeafClover extends Trinket {

	{
		image = 物品表.CLOVER;
	}

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (已鉴定()){
			return Messages.get(this, "stats_desc", Math.round(100*alterHeroDamageChance(等级())), Math.round(100f- 100*alterHeroDamageChance(等级())));
		} else {
			return Messages.get(this, "stats_desc", Math.round(100*alterHeroDamageChance(0)), Math.round(10f- 100*alterHeroDamageChance(0)));
		}
	}

	public static float alterHeroDamageChance(){
		return alterHeroDamageChance(trinketLevel(ThirteenLeafClover.class));
	}

	public static float alterHeroDamageChance(int level ){
		if (level < 0){
			return 0;
		} else {
			return 0.55f + 0.004f*level;
		}
	}


	public static float alterDamageRoll(float min, float max){
		if (Random.Float() < alterHeroDamageChance()){
			return max;
		} else {
			return min;
		}
	}

}

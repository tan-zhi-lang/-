

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
		if (isIdentified()){
			return Messages.get(this, "stats_desc", Math.round(MAX_CHANCE * 100*alterHeroDamageChance(buffedLvl())), Math.round((1f-MAX_CHANCE) * 100*alterHeroDamageChance(buffedLvl())));
		} else {
			return Messages.get(this, "typical_stats_desc", Math.round(MAX_CHANCE * 100*alterHeroDamageChance(0)), Math.round((1f-MAX_CHANCE) * 100*alterHeroDamageChance(0)));
		}
	}

	public static float alterHeroDamageChance(){
		return alterHeroDamageChance(trinketLevel(ThirteenLeafClover.class));
	}

	public static float alterHeroDamageChance(int level ){
		if (level <= -1){
			return 0;
		} else {
			return 0.25f + 0.25f*level;
		}
	}

	private static float MAX_CHANCE = 0.6f;

	public static int alterDamageRoll(int min, int max){
		if (Random.Float() < MAX_CHANCE){
			return max;
		} else {
			return min;
		}
	}

}

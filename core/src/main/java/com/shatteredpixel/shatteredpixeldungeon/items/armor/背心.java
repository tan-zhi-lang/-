

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 背心 extends Armor {

	{
		image = 物品表.背心;
	}

	public 背心(){
		super(1);
	}
	@Override
	public int 力量(int lvl) {
		int req = 力量(tier, lvl)-1;
		if (masteryPotionBonus){
			req -= 2;
		}
		return req;
	}
	
	public int 最大防御(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return tier + lvl + augment.defenseFactor(lvl);
		}
		
		int max = tier * (2 + lvl) + augment.defenseFactor(lvl)-1;
		if (lvl > max){
			return ((lvl - max)+1)/2;
		} else {
			return max;
		}
	}
}


package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 背心 extends Armor {

	{
		image = 物品表.背心;
		嬗变= false;
		专属=true;
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
		if (神力){
			req -= 2;
		}
		return req;
	}
	
	@Override
	public int 最小防御(int lvl){
		return super.最小防御(lvl)-augment.defenseFactor(tier + lvl);
	}
	public int 最大防御(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return augment.defenseFactor(tier + lvl);
		}
		
		int max = augment.defenseFactor(Math.round(tier * (2 + lvl)*1.25f));
		if (lvl > max){
			return ((lvl - max)+1)/2;
		} else {
			return max;
		}
	}
	@Override
	public int 金币() {
		return Math.round(super.金币()*1.34f);
	}
	@Override
	public int 能量() {
		return Math.round(super.能量()*1.34f);
	}
}
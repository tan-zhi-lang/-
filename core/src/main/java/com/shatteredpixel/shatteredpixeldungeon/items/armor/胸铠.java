

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 胸铠 extends Armor {

	{
		image = 物品表.ARMOR_DUELIST;
		嬗变= false;
		专属=true;
	}

	public 胸铠(){
		super(1);
	}
	public int 最大防御(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return augment.defenseFactor(tier + lvl);
		}
		
		int max = augment.defenseFactor(Math.round(tier * (2 + lvl)*1.5f) );
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
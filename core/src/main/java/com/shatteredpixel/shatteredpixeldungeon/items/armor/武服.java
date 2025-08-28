

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 武服 extends Armor {

	{
		image = 物品表.武服;
	}

	public 武服(){
		super(1);
	}

	@Override
	public int 最小防御(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return tier+1;
		}

		int max = 最大防御(lvl);
		if (lvl >= max){
			return (lvl - max)+tier+1;
		} else {
			return lvl+tier+1;
		}
	}
}
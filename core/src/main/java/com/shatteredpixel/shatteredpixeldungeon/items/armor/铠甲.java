

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 铠甲 extends Armor {

	{
		image = 物品表.ARMOR_WARRIOR;
	}
	public 铠甲(){
        super(2);
    }
	
	
	@Override
	public int 最小防御(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return tier+1;
		}
		return lvl+tier+1;
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
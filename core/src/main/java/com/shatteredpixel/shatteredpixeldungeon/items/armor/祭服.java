

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 祭服 extends Armor {

	{
		image = 物品表.ARMOR_CLERIC;
	}

	public 祭服(){
		super(1);
	}

	@Override
	public int 等级() {
		int level = super.等级()+1;
		return level;
	}
	@Override
	public int 力量(int lvl) {
		int req = 力量(tier, lvl)-1;
		if (masteryPotionBonus){
			req -= 2;
		}
		return req;
	}
}
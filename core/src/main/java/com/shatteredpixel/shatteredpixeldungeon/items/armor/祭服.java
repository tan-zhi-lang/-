

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
	public int 金币() {
		return Math.round(super.金币()*1.34f);
	}
	@Override
	public int 能量() {
		return Math.round(super.能量()*1.34f);
	}
}
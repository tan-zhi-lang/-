

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 训服 extends Armor {

	{
		image = 物品表.训服;
		嬗变= false;
		专属=true;
	}

	public 训服(){
		super(1);
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
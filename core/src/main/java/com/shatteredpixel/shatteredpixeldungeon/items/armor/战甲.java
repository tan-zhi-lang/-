

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战甲 extends Armor {

	{
		image = 物品表.战甲;
	}
	public 战甲(){
        super(1);
    }
	
	@Override
	public int 最大防御(int lvl){
		return super.最大防御(lvl)+tier+1;
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
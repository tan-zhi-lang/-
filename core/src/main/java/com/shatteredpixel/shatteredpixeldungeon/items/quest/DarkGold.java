

package com.shatteredpixel.shatteredpixeldungeon.items.quest;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class DarkGold extends Item {
	
	{
		image = 物品表.ORE;
		
		stackable = true;
		unique = true;
	}
	
	@Override
	public boolean 可升级() {
		return false;
	}
	
	@Override
	public boolean 已鉴定() {
		return true;
	}

	@Override
	public int 金币() {
		int price = 25* quantity;
		return price;
	}
	@Override
	public int 能量() {
		return Math.round(金币()*0.15f);
	}
}

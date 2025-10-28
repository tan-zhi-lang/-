

package com.shatteredpixel.shatteredpixeldungeon.items.quest;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class DarkGold extends Item {
	
	{
		image = 物品表.ORE;
		
		可堆叠= true;
		特别= true;
		物品 = true;
	}
	
	@Override
	public int 金币() {
		int price = 25* quantity;
		return price;
	}
	@Override
	public int 能量() {
		return Math.round(金币()*0.025f+1);
	}
}

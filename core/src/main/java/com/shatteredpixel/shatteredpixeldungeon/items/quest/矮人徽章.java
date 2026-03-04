

package com.shatteredpixel.shatteredpixeldungeon.items.quest;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 矮人徽章 extends Item {
	
	{
		image = 物品表.TOKEN;
		
		可堆叠= true;
		特别= true;
		物品 = true;
	}

	@Override
	public int 金币() {
		return 25 * quantity;
	}
}

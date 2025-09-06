

package com.shatteredpixel.shatteredpixeldungeon.items.quest;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class MetalShard extends Item {
	
	{
		image = 物品表.SHARD;
		stackable = true;
		物品 = true;
	}
	
	
	@Override
	public int 金币() {
		return quantity * 50;
	}

	@Override
	public int 能量() {
		return quantity * 3;
	}
}

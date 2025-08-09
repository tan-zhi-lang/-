

package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 绒布袋 extends Bag {

	{
		image = 物品表.绒布袋;
	}

	@Override
	public boolean canHold( Item item ) {
		if (item instanceof Plant.Seed || item instanceof Runestone
				|| item instanceof GooBlob || item instanceof MetalShard){
			return super.canHold(item);
		} else {
			return false;
		}
	}

	public int capacity(){
		return 19;
	}
	
	@Override
	public int value() {
		return 30;
	}

}

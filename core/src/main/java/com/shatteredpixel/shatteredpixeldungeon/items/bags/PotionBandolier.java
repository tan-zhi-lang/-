

package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;

public class PotionBandolier extends Bag {

	{
		image = 物品表.BANDOLIER;
	}

	@Override
	public boolean canHold( Item item ) {
		if (item instanceof Potion || item instanceof LiquidMetal|| item instanceof 水袋){
			return super.canHold(item);
		} else {
			return false;
		}
	}

	public int capacity(){
		return 19+(Dungeon.解压(解压设置.超级背包)?10:0);
	}

	@Override
	public int 金币() {
		return 40;
	}

}

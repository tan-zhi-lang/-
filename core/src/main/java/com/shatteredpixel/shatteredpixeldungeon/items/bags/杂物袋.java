

package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.Trinket;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;

public class 杂物袋 extends Bag {

	{
		image = 物品表.杂物袋;
	}

	@Override
	public boolean canHold( Item item ) {
		if (item instanceof Food||
			item instanceof Artifact||
			item instanceof Ring||
			item instanceof Armor||
			item instanceof Trinket){
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
		return 30;
	}

}

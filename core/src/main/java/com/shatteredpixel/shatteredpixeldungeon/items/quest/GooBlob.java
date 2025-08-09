

package com.shatteredpixel.shatteredpixeldungeon.items.quest;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class GooBlob extends Item {
	
	{
		image = 物品表.BLOB;
		stackable = true;
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public int value() {
		return quantity * 30;
	}

	@Override
	public int energyVal() {
		return quantity * 3;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.quest;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class DwarfToken extends Item {
	
	{
		image = 物品表.TOKEN;
		
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
}

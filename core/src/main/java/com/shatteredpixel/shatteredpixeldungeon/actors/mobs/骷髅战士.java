

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.单手剑;
import com.shatteredpixel.shatteredpixeldungeon.sprites.骷髅战士动画;

public class 骷髅战士 extends Skeleton {
	
	{
		spriteClass = 骷髅战士动画.class;
		
		生命 = 最大生命 = 35;
		经验 = 10;

		loot =单手剑.class;

		properties.add(Property.UNDEAD);
		properties.add(Property.INORGANIC);
	}
	
	@Override
	public int 最小攻击() {
		return 3;
	}
	@Override
	public int 最大攻击() {
		return 19;
	}
	@Override
	public Item createLoot() {
		Dungeon.LimitedDrops.SKELE_WEP.count--;
		return super.createLoot();
	}
}

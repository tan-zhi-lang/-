

package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.EnergyCrystal;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.Trinket;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.属性碎片;
import com.shatteredpixel.shatteredpixeldungeon.items.属性锻造器;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯秘卷;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯移除器;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;

public class 宝物袋 extends Bag {

	{
		image = 物品表.宝物袋;
	}

	@Override
	public boolean canHold( Item item ) {
		if(Dungeon.符文("超级背包"))
			return super.canHold(item);
		if (item instanceof Artifact||
			item instanceof Ring||

			item instanceof Weapon||

			item instanceof Armor||
			item instanceof Ankh||

			item instanceof 海克斯卷轴||
			item instanceof 海克斯秘卷||
			item instanceof 海克斯宝典||
			item instanceof 海克斯移除器||

			item instanceof 属性碎片||
			item instanceof 属性锻造器||

			item instanceof Gold||
			item instanceof EnergyCrystal||

			item instanceof Trinket){
			item.价值提升=true;
			return super.canHold(item);
		} else {
			return false;
		}
	}
	
	public int capacity(){
		return 19+(Dungeon.解压(解压设置.高级背包)?10:0);
	}
	
	@Override
	public int 金币() {
		return 30;
	}

}

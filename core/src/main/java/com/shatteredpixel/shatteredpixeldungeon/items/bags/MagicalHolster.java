

package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;

public class MagicalHolster extends Bag {

	{
		image = 物品表.HOLSTER;
	}

	public static final float HOLSTER_SCALE_FACTOR = 0.85f;
	
	@Override
	public boolean canHold( Item item ) {
		if (item instanceof Wand||item instanceof Weapon||item instanceof Bomb){
			return super.canHold(item);
		} else {
			return false;
		}
	}

	public int capacity(){
		return 19+(Dungeon.解压(解压设置.超级背包)?10:0);
	}
	
	@Override
	public boolean 放背包(Bag container ) {
		if (super.放背包( container )) {
			if (owner != null) {
				for (Item item : items) {
					if (item instanceof Wand) {
						((Wand) item).charge(owner, HOLSTER_SCALE_FACTOR);
					}
					if (item instanceof Weapon) {
						((Weapon) item).charge(owner, HOLSTER_SCALE_FACTOR);
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onDetach( ) {
		super.onDetach();
		for (Item item : items) {
			if (item instanceof Wand) {
				((Wand)item).stopCharging();
			}
		}
	}
	
	@Override
	public int 金币() {
		return 60;
	}

}

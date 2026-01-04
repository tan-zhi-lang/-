

package com.shatteredpixel.shatteredpixeldungeon.items.bags;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.ArcaneResin;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Stylus;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.BeaconOfReturning;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Spell;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;

public class ScrollHolder extends Bag {

	{
		image = 物品表.HOLDER;
	}

	@Override
	public boolean canHold( Item item ) {
		if (item instanceof Scroll || item instanceof Spell
				|| item instanceof ArcaneResin || item instanceof Stylus){
			item.价值提升=true;
			return super.canHold(item);
		} else {
			return false;
		}
	}
	
	public int capacity(){
		return 19+(Dungeon.解压(解压设置.超级背包)?10:0);
	}
	
	@Override
	public void onDetach( ) {
		super.onDetach();
		for (Item item : items) {
			if (item instanceof BeaconOfReturning && ((BeaconOfReturning) item).returnDepth != -1) {
				Notes.remove(Notes.Landmark.BEACON_LOCATION, ((BeaconOfReturning) item).returnDepth);
				((BeaconOfReturning) item).returnDepth = -1;
			}
		}
	}
	
	@Override
	public int 金币() {
		return 40;
	}

}

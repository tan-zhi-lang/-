

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 闪避之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_EVASION;
		buffClass = Evasion.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   soloBuffedBonus()*2);
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						combinedBuffedBonus(Dungeon.hero)*2);
			}
			return info;
		} else {
			return Messages.get(this, "stats", 2);
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return ""+level;
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Evasion();
	}

	public class Evasion extends RingBuff {
	}
}

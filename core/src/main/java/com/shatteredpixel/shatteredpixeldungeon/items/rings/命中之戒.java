

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 命中之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_ACCURACY;
		buffClass = Accuracy.class;
	}
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   soloBuffedBonus()*2,soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						combinedBuffedBonus(Dungeon.hero)*2,combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats", 2,1);
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return 2*level+"";
	}
	public String upgradeStat2(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return level+"";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Accuracy();
	}

	public class Accuracy extends RingBuff {
	}
}

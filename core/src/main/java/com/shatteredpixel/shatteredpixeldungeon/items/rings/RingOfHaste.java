

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class RingOfHaste extends Ring {

	{
		icon = 物品表.Icons.RING_HASTE;
		buffClass = Haste.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.##", Math.pow(1.175f, soloBuffedBonus()) - 1f));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.##", Math.pow(1.175f, combinedBuffedBonus(Dungeon.hero)) - 1f));
			}
			return info;
		} else {
			return Messages.get(this, "stats", Messages.decimalFormat("#.##", 0.175f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", Math.pow(1.175f, level+1)-1f) + "倍";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Haste();
	}
	
	public static float speedMultiplier( Char target ){
		return (float)Math.pow(1.175, getBuffedBonus(target, Haste.class));
	}
	
	public class Haste extends RingBuff {
	}
}

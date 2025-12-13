

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 六神之戒 extends Ring {

	{
		icon = 物品表.Icons.六神之戒;
		buffClass = 六神.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   Messages.decimalFormat("#.2", Math.pow(1.07f, soloBuffedBonus()) - 1f));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  Messages.decimalFormat("#.2", Math.pow(1.07f, combinedBuffedBonus(Dungeon.hero)) - 1f));
			}
			return info;
		} else {
			return Messages.get(this, "stats",.07f);
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.2", Math.pow(1.07f, level+1)-1f) + "倍";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new 六神();
	}

	public static float 六神之力( Char target ){
		return (float)Math.pow(1.07f, getBuffedBonus(target, 六神.class));
	}
	
	public class 六神 extends RingBuff {
	}
}

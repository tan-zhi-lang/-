

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
									   Messages.decimalFormat("#.2", 0.618f*soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  Messages.decimalFormat("#.2", 0.618f*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats",.0618f);
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return Messages.decimalFormat("#.2", 0.618f*(level+1)) + "倍";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new 六神();
	}

	public static float 六神之力( Char target ){
		return 1+0.618f*getBuffedBonus(target, 六神.class);
	}
	
	public class 六神 extends RingBuff {
	}
}

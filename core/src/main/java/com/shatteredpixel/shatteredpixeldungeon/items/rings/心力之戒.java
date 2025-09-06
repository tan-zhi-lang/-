

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 心力之戒 extends Ring {

	{
		icon = 物品表.Icons.心力之戒;
		buffClass = 心力.class;
	}
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   Messages.decimalFormat("#.##", Math.pow(1.07f, soloBuffedBonus()) - 1f));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  Messages.decimalFormat("#.##", Math.pow(1.07f, combinedBuffedBonus(Dungeon.hero)) - 1f));
			}
			return info;
		} else {
			return Messages.get(this, "typical_stats",
								Messages.decimalFormat("#.##", 0.07f));
		}
	}
	
	
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", Math.pow(1.07f, level+1)-1f) + "倍";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new 心力();
	}
	
	public static float 心力( Char target ){
		return (float)Math.pow(1.1f, getBuffedBonus(target, 心力.class));
	}
	
	public class 心力 extends RingBuff {
	}
}

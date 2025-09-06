

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 时间之戒 extends Ring {

	{
		icon = 物品表.Icons.时间之戒;
		buffClass = 时间.class;
	}
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   Messages.decimalFormat("#.##", Math.pow(0.92f, soloBuffedBonus())));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  Messages.decimalFormat("#.##", Math.pow(0.92f, combinedBuffedBonus(Dungeon.hero))));
			}
			return info;
		} else {
			return Messages.get(this, "typical_stats",
								Messages.decimalFormat("#.##", 92));
		}
	}
	
	
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", Math.pow(0.92, level+1)) + "%";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new 时间();
	}
	
	public static float 时间( Char target ){
		return (float)Math.pow(0.92f, getBuffedBonus(target, 时间.class));
	}
	
	public class 时间 extends RingBuff {
	}
}

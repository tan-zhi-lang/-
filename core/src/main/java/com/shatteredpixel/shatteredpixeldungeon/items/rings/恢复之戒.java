

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 恢复之戒 extends Ring {

	{
		icon = 物品表.Icons.恢复之戒;
		buffClass = 恢复.class;
	}
	
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   Messages.decimalFormat("#.2", Math.pow(1.2f, soloBuffedBonus()) - 1f));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  Messages.decimalFormat("#.2", Math.pow(1.2f, combinedBuffedBonus(Dungeon.hero)) - 1f));
			}
			return info;
		} else {
			return Messages.get(this, "stats",.2f);
		}
	}
	
	
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.2", Math.pow(1.2f, level+1)-1f) + "倍";
	}
	public static float 恢复( Char target){
		return (float)Math.pow(1.2f, getBuffedBonus(target, 恢复.class));
	}
	@Override
	protected RingBuff buff( ) {
		return new 恢复();
	}
	
	public class 恢复 extends RingBuff {
	}
}

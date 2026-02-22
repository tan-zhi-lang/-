

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
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
									   soloBuffedBonus()*0.236f,soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						combinedBuffedBonus(Dungeon.hero)*0.236f,combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats", 0.236f,1);
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return Messages.decimalFormat("#.2f", 0.236f*(level+1)) + "倍";
	}

	public static float 命中(Char target){
		return 1+0.236f*getBuffedBonus(target, 狂怒之戒.Furor.class);
	}
	@Override
	protected RingBuff buff( ) {
		return new Accuracy();
	}

	public class Accuracy extends RingBuff {
	}
}

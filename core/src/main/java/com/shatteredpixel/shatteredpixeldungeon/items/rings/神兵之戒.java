

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 神兵之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_SHARPSHOOT;
		buffClass = Aim.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					0.75f*soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						0.75f*combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats", 0.75f);
		}
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return Integer.toString(level);
	}
	@Override
	protected RingBuff buff( ) {
		return new Aim();
	}
	
	public static int levelBonus(Char target){
		return getBuffedBonus(target, 神兵之戒.Aim.class);
	}

	public class Aim extends RingBuff {
	}
}

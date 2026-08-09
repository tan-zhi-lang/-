

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 魔攻之戒 extends Ring {

	{
		icon = 物品表.Icons.魔攻之戒;
		buffClass = 奥术之戒.Arcana.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   1.5f * soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  1.5f*combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats", 1.5f);
		}
	}
	public static int 魔力(Char target){
		return getBuffedBonus( target, 奥术之戒.Arcana.class);
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+(1.5f+1.5f*level);
	}
	@Override
	protected RingBuff buff( ) {
		return new 魔攻();
	}

	public class 魔攻 extends RingBuff {
	}

}

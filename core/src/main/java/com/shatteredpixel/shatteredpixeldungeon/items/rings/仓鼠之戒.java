

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 仓鼠之戒 extends Ring {

	{
		icon = 物品表.Icons.仓鼠之戒;
		buffClass = 格子.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(3 * soloBuffedBonus()),
									   kw2(50 * soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  kw2(3*combinedBuffedBonus(Dungeon.hero)),
											  kw2(50*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats", kw2(3), kw2(50));
		}
	}
	public static int 格子(Char target){
		return 3*getBuffedBonus( target, 格子.class);
	}
	public static int 饱腹(Char target){
		return 50*getBuffedBonus( target, 格子.class);
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+(3+3*level);
	}
	@Override
	public String upgradeStat2(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+(50+50*level);
	}
	@Override
	protected RingBuff buff( ) {
		return new 格子();
	}

	public class 格子 extends RingBuff {
	}

}

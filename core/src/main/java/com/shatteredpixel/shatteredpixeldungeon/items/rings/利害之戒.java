

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 利害之戒 extends Ring {

	{
		icon = 物品表.Icons.利害之戒;
		buffClass = 利害.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   0.05f*soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  0.05f*combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats",
								0.05f);
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 0.05f*(level) + "倍";
	}

	@Override
	protected RingBuff buff( ) {
		return new 利害();
	}
	
	public static float 暴击率(Char target){
		return 0.05f*getBuffedBonus(target, 利害.class);
	}

	public class 利害 extends RingBuff {
	}
}

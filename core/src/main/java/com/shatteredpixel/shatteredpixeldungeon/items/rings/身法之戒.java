

package com.shatteredpixel.shatteredpixeldungeon.items.rings;


import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 身法之戒 extends Ring {

	{
		icon = 物品表.Icons.身法之戒;
		buffClass = 身法.class;
	}

	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   3.5f*soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  3.5f*combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats", 3.5f);
		}
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+(3.5f+3.5f*level);
	}

	
	public static float 敏捷( Char target ){
		return getBuffedBonus( target, 身法.class)*3.5f;
	}
	
	
	@Override
	protected RingBuff buff( ) {
		return new 身法();
	}
	public class 身法 extends RingBuff {
	}
}


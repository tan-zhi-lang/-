

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 神射之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_SHARPSHOOT;
		buffClass = Aim.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					0.08f*soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						0.08f*combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats", 0.08f);
		}
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+level*0.08f;
	}
	@Override
	protected RingBuff buff( ) {
		return new Aim();
	}
	
	public static float levelBonus(Char target){
		return 0.08f*getBuffedBonus(target, 神射之戒.Aim.class);
	}

	public class Aim extends RingBuff {
	}
}

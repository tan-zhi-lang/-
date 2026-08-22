

package com.shatteredpixel.shatteredpixeldungeon.items.rings;


import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 法爆之戒 extends Ring {

	{
		icon = 物品表.Icons.法爆之戒;
		buffClass = 法爆.class;
	}
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(2.5f*soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  kw2(2.5f*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats",  kw2(2.5f));
		}
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+(2.5f+2.5f*level);
	}

	
	public static float 法爆(Char target){
		return getBuffedBonus( target, 法爆.class)*2.5f;
	}
	
	
	@Override
	protected RingBuff buff( ) {
		return new 法爆();
	}
	public class 法爆 extends RingBuff {
	}
}


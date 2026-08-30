

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 汲取之戒 extends Ring {

	{
		icon = 物品表.Icons.汲取之戒;
		buffClass = 汲取.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(0.005f*100*soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  kw2(0.005f*100*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats",
								kw2(0.005f*100));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 0.005f*(level) + "%";
	}

	@Override
	protected RingBuff buff( ) {
		return new 汲取();
	}
	
	public static float 吸血(Char target){
		return 0.005f*getBuffedBonus(target, 汲取.class);
	}

	public class 汲取 extends RingBuff {
	}
}

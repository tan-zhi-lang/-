

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 强健之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_FUROR;
		buffClass = 强健.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(0.12f*soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
							  kw2(0.12f*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats",
								kw2(0.12f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 0.12f*(level) + "倍";
	}

	@Override
	protected RingBuff buff( ) {
		return new 强健();
	}
	
	public static float 最大生命(Char target){
		return 1+0.12f*getBuffedBonus(target, 强健.class);
	}

	public class 强健 extends RingBuff {
	}
}

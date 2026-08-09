

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 钢壁之戒 extends Ring {

	{
		icon = 物品表.Icons.钢壁之戒;
		buffClass = 钢壁.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(0.25f*soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
							  kw2(0.25f*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats",
								kw2(0.25f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 0.25f*(level) + "倍";
	}

	@Override
	protected RingBuff buff( ) {
		return new 钢壁();
	}
	
	public static float 最大护甲(Char target){
		return 1+0.25f*getBuffedBonus(target, 钢壁.class);
	}

	public class 钢壁 extends RingBuff {
	}
}

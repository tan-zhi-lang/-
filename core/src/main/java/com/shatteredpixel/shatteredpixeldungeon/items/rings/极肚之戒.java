

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 极肚之戒 extends Ring {

	{
		icon = 物品表.Icons.极肚之戒;
		buffClass = 极肚.class;
	}


	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(100f * (float)(1f - Math.pow(0.68f, soloBuffedBonus()))));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  kw2(100f * (float)(1f - Math.pow(0.68f, combinedBuffedBonus(Dungeon.hero)))));
			}
			return info;
		} else {
			return Messages.get(this, "stats",    kw2(32f));
		}
	}
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 100f * (1f - Math.pow(0.68f, level)) + "%";
	}
	public static float 饥饿速度( Char target){
		return (float)Math.pow(0.68,getBuffedBonus( target, 极肚.class));
	}
	@Override
	protected RingBuff buff( ) {
		return new 极肚();
	}
	
	public class 极肚 extends RingBuff {
	}
}

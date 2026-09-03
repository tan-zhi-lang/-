

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 果刚之戒 extends Ring {

	{
		icon = 物品表.Icons.果刚之戒;
		buffClass = 果刚.class;
	}


	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(100f * (float)(1f - Math.pow(0.9835f, soloBuffedBonus()))));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  kw2(100f * (float)(1f - Math.pow(0.9835f, combinedBuffedBonus(Dungeon.hero)))));
			}
			return info;
		} else {
			return Messages.get(this, "stats",    kw2(1.65f));
		}
	}
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 100f * (1f - Math.pow(0.9835f, level)) + "%";
	}
	public static float 免疫(Char target){
		return (float)Math.pow(0.9835f,getBuffedBonus( target, 果刚.class));
	}
	@Override
	protected RingBuff buff( ) {
		return new 果刚();
	}
	
	public class 果刚 extends RingBuff {
	}
}

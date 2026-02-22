

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 疾速之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_HASTE;
		buffClass = Haste.class;
	}
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   Messages.decimalFormat("#.2", 0.21025f * soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  Messages.decimalFormat("#.2", 0.21025f*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats", Messages.decimalFormat("#.2", 0.21025f));
		}
	}
	
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return Messages.decimalFormat("#.2", 0.21025f*(level+1)) + "倍";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Haste();
	}
	
	public static float speedMultiplier( Char target ){
		return 1+0.21025f*getBuffedBonus(target, RingOfElements.Resistance.class);
	}
	
	public class Haste extends RingBuff {
	}
}

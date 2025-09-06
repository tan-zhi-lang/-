

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 全知之戒 extends Ring {

	{
		icon = 物品表.Icons.全知之戒;
		buffClass = 全知.class;
	}
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",soloBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",1);
			}
			return info;
		} else {
			return Messages.get(this, "typical_stats",1);
		}
	}
	
	
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return ""+level;
	}
	
	public static int 全知之力( Char target){
		return getBonus( target, 全知.class);
	}
	@Override
	protected RingBuff buff( ) {
		return new 全知();
	}
	
	public class 全知 extends RingBuff {
	}
}

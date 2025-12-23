

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class RingOfSharpshooting extends Ring {

	{
		icon = 物品表.Icons.RING_SHARPSHOOT;
		buffClass = Aim.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats", 1);
		}
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Integer.toString(level);
	}
	@Override
	protected RingBuff buff( ) {
		return new Aim();
	}
	
	public static int levelDamageBonus( Char target ){
		int x=0;
		if(target instanceof Hero hero){
		}
		return getBuffedBonus(target, RingOfSharpshooting.Aim.class)+x;
	}

	public class Aim extends RingBuff {
	}
}

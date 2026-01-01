

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class RingOfTenacity extends Ring {

	{
		icon = 物品表.Icons.RING_TENACITY;
		buffClass = Tenacity.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.2", 100f * Math.pow(0.85f, soloBuffedBonus())));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.2", 100f * Math.pow(0.85f, combinedBuffedBonus(Dungeon.hero))));
			}
			return info;
		} else {
			return Messages.get(this, "stats", Messages.decimalFormat("#.2", 15f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.2", 100f * Math.pow(0.85f, level+1)) + "%";
	}

	@Override
	protected RingBuff buff( ) {
		return new Tenacity();
	}
	
	public static float damageMultiplier( Char t ){
		//(HT - HP)/HT = heroes current % missing health.
		return (float)Math.pow(0.85, getBuffedBonus( t, Tenacity.class)*((float)(t.最大生命 - t.生命)/t.最大生命));
	}

	public class Tenacity extends RingBuff {
	}
}


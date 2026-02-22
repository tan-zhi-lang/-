

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 奥术之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_ARCANA;
		buffClass = Arcana.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.2", 0.21025f*soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.2", 0.21025f*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats", Messages.decimalFormat("#.2", 0.175f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed) level = Math.min(-1, level-6);
		return Messages.decimalFormat("#.2", 0.21025f*(level+1)) + "倍";
	}

	@Override
	protected RingBuff buff( ) {
		return new Arcana();
	}

	public static float enchantPowerMultiplier(Char target ){
		return 1+0.21025f*getBuffedBonus(target, Arcana.class);
	}

	public class Arcana extends RingBuff {
	}

}

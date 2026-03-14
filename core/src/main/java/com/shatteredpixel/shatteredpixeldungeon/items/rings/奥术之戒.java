

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
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
					10*soloBuffedBonus(),Dungeon.hero.魔力()/10f*0.21025f);
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						10*combinedBuffedBonus(Dungeon.hero),Dungeon.hero.魔力()/10f*0.21025f);
			}
			return info;
		} else {
			return Messages.get(this, "stats", 10);
		}
	}
	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+(10+10*level);
	}
	@Override
	protected RingBuff buff( ) {
		return new Arcana();
	}
	public static int 奥术( Char target ){
		return getBuffedBonus( target, Arcana.class);
	}

	public static float enchantPowerMultiplier(Char target ){
		float x=1;
		if(target instanceof Hero hero)
			x+=(hero.魔力()-10)/10f*0.21025f;

		return x;
	}

	public class Arcana extends RingBuff {
	}

}

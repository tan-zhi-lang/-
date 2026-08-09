

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 杀戮之戒 extends Ring {

	{
		icon = 物品表.Icons.杀戮之戒;
		buffClass = 杀戮属性.class;
	}
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(0.005f*soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
							  kw2(0.005f*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats", kw2(0.005f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 0.005f*(level)+"倍";
	}


	public static float 属性倍(Char target){
		return 0.005f*getBuffedBonus( target, 杀戮属性.class);
	}

	@Override
	protected RingBuff buff( ) {
		return new 杀戮属性();
	}

	public class 杀戮属性 extends RingBuff {
	}

}

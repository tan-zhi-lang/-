

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 装甲之戒 extends Ring {

	{
		icon = 物品表.Icons.装甲之戒;
		buffClass = 装甲.class;
	}
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(tier()*(1+soloBuffedBonus()/2f)));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  kw2(tier()*(1+combinedBuffedBonus(Dungeon.hero)/2f)));
			}
			return info;
		} else {
			return Messages.get(this, "stats",kw2(1));
		}
	}
	
	
	public static int tier(){
		float str=Dungeon.hero!=null?
				Dungeon.hero.力量():
				10;
		int tier=Math.round(Math.max(1,(str-8)/2f));
		//each str point after 18 is half as effective
		if(tier>5){
			tier=5+Math.round((tier-5)/2f);
		}
		return tier;
	}

	public static float max(){
		int x=0;
		return max(x,tier());
	}

	public static float max(int lvl,float tier){
		if(lvl<=0){
			lvl=0;
		}

		return Math.max(0,tier*(lvl+1)
					   )*2;
	}
	@Override
	public String upgradeStat1(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-6);
		}
		return ""+(tier()*(1+level));
	}
	@Override
	protected RingBuff buff( ) {
		return new 装甲();
	}
	
	public class 装甲 extends RingBuff {
	}
}

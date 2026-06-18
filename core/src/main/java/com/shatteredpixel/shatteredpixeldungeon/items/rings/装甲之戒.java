

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

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
									   tier()+3/2f+soloBuffedBonus()/2f,
									   tier()*(2+3/2f+soloBuffedBonus()/2f),
									   (tier()+3/2f+soloBuffedBonus()/2f));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  tier()+combinedBuffedBonus(Dungeon.hero),
											 tier()*(2+3/2f+combinedBuffedBonus(Dungeon.hero)/2f),
				
				(tier()+3/2f+combinedBuffedBonus(Dungeon.hero)/2f));
			}
			return info;
		} else {
			return Messages.get(this, "stats",1+3/2f,2+3/2f,1+3/2f);
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
	@Override
	public String upgradeStat1(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-6);
		}
		return (tier()+3/2f+level/2f)+"~"+(tier()*(2+3/2f+level/2f));
	}
	@Override
	public String upgradeStat2(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-6);
		}
		return ""+(tier()+3/2f+level/2f);
	}
	@Override
	protected RingBuff buff( ) {
		return new 装甲();
	}
	
	public class 装甲 extends RingBuff {
	}
}

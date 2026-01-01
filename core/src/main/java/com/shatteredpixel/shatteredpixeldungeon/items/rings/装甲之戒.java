

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
									   tier()+soloBuffedBonus(),
									   Math.round(2*tier()*(1+soloBuffedBonus()/1.5f)),
									   Math.round(2*(1+soloBuffedBonus()/1.5f)));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  tier()+combinedBuffedBonus(Dungeon.hero),
											  Math.round(2*tier()*(1+combinedBuffedBonus(Dungeon.hero)/1.5f)),
				
				Math.round(2*(1+combinedBuffedBonus(Dungeon.hero)/1.5f)));
			}
			return info;
		} else {
			return Messages.get(this, "stats",3,7,3);
		}
	}
	
	
	public static int tier(){
		int str=Dungeon.hero!=null?
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
			level=Math.min(-1,level-3);
		}
		return (tier()+level)+"~"+Math.round(2*tier()*(1+level/1.5f));
	}
	@Override
	public String upgradeStat2(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-3);
		}
		return ""+Math.round((2*(1+level/1.5f)));
	}
	@Override
	protected RingBuff buff( ) {
		return new 装甲();
	}
	
	public class 装甲 extends RingBuff {
	}
}

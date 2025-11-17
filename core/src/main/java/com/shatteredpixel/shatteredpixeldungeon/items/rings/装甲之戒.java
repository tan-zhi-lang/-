

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
									   soloBuffedBonus(),tier()*(1+soloBuffedBonus()),soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  combinedBuffedBonus(Dungeon.hero),notier()*(1+combinedBuffedBonus(Dungeon.hero)),combinedBuffedBonus(Dungeon.hero));
			}
			return info;
		} else {
			return Messages.get(this, "stats",0,2,1);
		}
	}
	
	
	public static int tier(){
		int str=Dungeon.hero!=null?
				Dungeon.hero.力量()+2:
				12;
		int tier=Math.round(Math.max(1,(str-8)/2f));
		//each str point after 18 is half as effective
		if(tier>6){
			tier=6+Math.round((tier-6)/2f);
		}
		return tier;
	}
	
	public static int notier(){
		int str=12;
		int tier=Math.round(Math.max(1,(str-8)/2f));
		//each str point after 18 is half as effective
		if(tier>6){
			tier=6+Math.round((tier-6)/2f);
		}
		return tier;
	}
	@Override
	public String upgradeStat1(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-3);
		}
		return level+"~"+tier()*(1+level);
	}
	@Override
	public String upgradeStat2(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-3);
		}
		return "0~"+1+等级;
	}
	@Override
	protected RingBuff buff( ) {
		return new 装甲();
	}
	
	public class 装甲 extends RingBuff {
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 极肚之戒 extends Ring {

	{
		icon = 物品表.Icons.极肚之戒;
		buffClass = 极肚.class;
	}
	
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									  100*soloBuffedBonus(),0.005f*soloBuffedBonus()*100f);
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  100*combinedBuffedBonus(Dungeon.hero),0.005f*combinedBuffedBonus(Dungeon.hero)*100f);
			}
			return info;
		} else {
			return Messages.get(this, "stats",100,0.005f*100f);
		}
	}
	
	
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+100*(level+1);
	}
	public String upgradeStat2(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 0.005f*(level+1)+"%";
	}
	public static float 饥饿(Char target){
		return 100*getBuffedBonus(target, 极肚.class);
	}
	public static float 力量(Char target){
		return 0.005f*getBuffedBonus(target, 极肚.class);
	}
	@Override
	protected RingBuff buff( ) {
		return new 极肚();
	}
	
	public class 极肚 extends RingBuff {
	}
}

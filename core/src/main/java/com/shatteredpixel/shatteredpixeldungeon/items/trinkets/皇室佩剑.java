

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 皇室佩剑 extends Trinket {

	{
		image = 物品表.皇室佩剑;
	}

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (已鉴定()){
			return Messages.get(this,"stats_desc",
								概率(),
								回血(),
								亏损());
		} else {
			return Messages.get(this,"stats_desc",
								概率(0),
								回血(0),
								亏损(0));
		}
	}

	public static int 概率(){
		return 概率(trinketLevel(皇室佩剑.class));
	}

	public static int 概率(int level){
		if (level < 0){
			return 0;
		} else {
			return 15+5*level;
		}
	}
	public static float 回血(){
		return 概率(trinketLevel(皇室佩剑.class));
	}

	public static float 回血(int level){
		if (level < 0){
			return 0;
		} else {
			return 2+2*level;
		}
	}
	public static float 亏损(){
		return 亏损(trinketLevel(皇室佩剑.class));
	}

	public static float 亏损(int level){
		if (level < 0){
			return 0;
		} else {
			return -1-1*level;
		}
	}

}

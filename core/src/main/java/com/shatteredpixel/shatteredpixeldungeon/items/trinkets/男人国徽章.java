

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 男人国徽章 extends Trinket {

	{
		image = 物品表.男人国徽章;
	}

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (已鉴定()){
			return Messages.get(this, "stats_desc",
					Messages.decimalFormat("#.##", 100*力量()),
								受伤());
		} else {
			return Messages.get(this, "stats_desc",
					Messages.decimalFormat("#.##", 100*力量(0)),
								受伤(0));
		}
	}

	public static float 力量(){
		return 力量(trinketLevel(男人国徽章.class));
	}

	public static float 力量(int level){
		if (level < 0){
			return 1;
		} else {
			return 1.15f+ 0.075f*level;
		}
	}
	public static float 受伤(){
		return 受伤(trinketLevel(男人国徽章.class));
	}

	public static float 受伤(int level){
		if (level < 0){
			return 0;
		} else {
			return 0.5f - 0.5f*level;
		}
	}

}

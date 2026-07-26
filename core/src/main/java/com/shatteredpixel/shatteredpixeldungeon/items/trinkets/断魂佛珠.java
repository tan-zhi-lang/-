

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 断魂佛珠 extends Trinket {

	{
		image = 物品表.断魂佛珠;
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
								回血(),
								敏捷());
		} else {
			return Messages.get(this,"stats_desc",
								回血(0),
								敏捷(0));
		}
	}

	public static float 回血(){
		return 回血(trinketLevel(断魂佛珠.class));
	}

	public static float 回血(int level){
		if (level < 0){
			return 0;
		} else {
			return 5+5*level;
		}
	}
	public static float 敏捷(){
		return 敏捷(trinketLevel(断魂佛珠.class));
	}

	public static float 敏捷(int level){
		if (level < 0){
			return 0;
		} else {
			return -0.5f-0.5f*level;
		}
	}

}

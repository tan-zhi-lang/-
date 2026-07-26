

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 心源芯片 extends Trinket {

	{
		image = 物品表.心源芯片;
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
								主要(),
								其他());
		} else {
			return Messages.get(this,"stats_desc",
								主要(0),
								其他(0));
		}
	}

	public static float 主要(){
		return 主要(trinketLevel(心源芯片.class));
	}

	public static float 主要(int level){
		if (level < 0){
			return 0;
		} else {
			return 0.02f + 0.01f*level;
		}
	}
	public static float 其他(){
		return 其他(trinketLevel(心源芯片.class));
	}

	public static float 其他(int level){
		if (level < 0){
			return 0;
		} else {
			return -0.5f-0.5f*level;
		}
	}

}

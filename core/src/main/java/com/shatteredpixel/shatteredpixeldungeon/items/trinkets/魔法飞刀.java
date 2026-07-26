

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 魔法飞刀 extends Trinket {

	{
		image = 物品表.魔法飞刀;
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
								投掷(),
								魔力());
		} else {
			return Messages.get(this, "stats_desc",
								投掷(0),
								魔力(0));
		}
	}

	public static float 投掷(){
		return 投掷(trinketLevel(魔法飞刀.class));
	}

	public static float 投掷(int level){
		if (level < 0){
			return 1;
		} else {
			return 1.5f + 0.335f*level;
		}
	}
	public static float 魔力(){
		return 魔力(trinketLevel(魔法飞刀.class));
	}

	public static float 魔力(int level){
		if (level < 0){
			return 0;
		} else {
			return -0.03f-0.04f*level;
		}
	}

}

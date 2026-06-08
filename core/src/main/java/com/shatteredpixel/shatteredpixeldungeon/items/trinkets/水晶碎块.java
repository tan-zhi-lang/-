

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 水晶碎块 extends Trinket {

	{
		image = 物品表.水晶碎块;
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
								100*护甲(),
								100*防御()
							   );
		} else {
			return Messages.get(this,"stats_desc",
								100*护甲(0),
								100*防御(0)
							   );
		}
	}

	public static float 防御(){
		return 防御(trinketLevel(水晶碎块.class));
	}

	public static float 防御(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.95f-0.05f*level;
		}
	}

	public static float 护甲(){
		return 护甲(trinketLevel(水晶碎块.class));
	}

	public static float 护甲(int level){
		if (level < 0){
			return 1;
		} else {
			return 1.5f+0.2f*level;
		}
	}

}

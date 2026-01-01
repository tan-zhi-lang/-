

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨大蟹钳 extends Trinket {

	{
		image = 物品表.巨大蟹钳;
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
								受到(),
								减少()
							   );
		} else {
			return Messages.get(this,"stats_desc",
								
								受到(0),
								减少(0)
							   );
		}
	}

	public static float 受到(){
		return 受到(trinketLevel(巨大蟹钳.class));
	}

	public static float 受到(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.95f-0.05f*level;
		}
	}

	public static float 减少(){
		return 减少(trinketLevel(巨大蟹钳.class));
	}

	public static float 减少(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.94f-0.03f*level;
		}
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圣金之沙 extends Trinket {

	{
		image = 物品表.圣金之沙;
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
								
								100*获得(),
								
								100*减少()
							   );
		} else {
			return Messages.get(this,"stats_desc",
								
								100*获得(0),
								
								100*减少(0)
							   );
		}
	}

	public static float 获得(){
		return 获得(trinketLevel(圣金之沙.class));
	}

	public static float 获得(int level){
		if (level < 0){
			return 0;
		} else {
			return 0.075f-0.025f*level;
		}
	}

	public static float 减少(){
		return 减少(trinketLevel(圣金之沙.class));
	}

	public static float 减少(int level){
		if (level < 0){
			return 0;
		} else {
			return -1.5f-0.5f*level;
		}
	}

}

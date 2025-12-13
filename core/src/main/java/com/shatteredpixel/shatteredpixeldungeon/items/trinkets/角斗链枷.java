

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 角斗链枷 extends Trinket {

	{
		image = 物品表.角斗链枷;
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
								暴击率(),
								Messages.decimalFormat("#.##",100*减少())
							   );
		} else {
			return Messages.get(this,"stats_desc",
								暴击率(0),
								Messages.decimalFormat("#.##",100*减少(0))
							   );
		}
	}

	public static float 减少(){
		return 减少(trinketLevel(角斗链枷.class));
	}

	public static float 减少(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.9f+0.05f*level;
		}
	}

	public static int 暴击率(){
		return 暴击率(trinketLevel(角斗链枷.class));
	}

	public static int 暴击率(int level){
		if (level < 0){
			return 0;
		} else {
			return 15+5*level;
		}
	}

}

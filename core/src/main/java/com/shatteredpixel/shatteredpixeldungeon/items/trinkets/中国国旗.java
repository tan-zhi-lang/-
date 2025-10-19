

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 中国国旗 extends Trinket {

	{
		image = 物品表.中国国旗;
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
					Messages.decimalFormat("#.##", 100*受伤(强化等级())),
								Messages.decimalFormat("#.##",100*移速(强化等级())));
		} else {
			return Messages.get(this, "typical_stats_desc",
					Messages.decimalFormat("#.##", 100*受伤(0)),
								Messages.decimalFormat("#.##",100*移速(0)));
		}
	}

	public static float 移速(){
		return 移速(trinketLevel(中国国旗.class));
	}

	public static float 移速(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.92f - 0.04f*level;
		}
	}
	public static float 受伤(){
		return 受伤(trinketLevel(中国国旗.class));
	}

	public static float 受伤(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.85f - 0.05f*level;
		}
	}

}

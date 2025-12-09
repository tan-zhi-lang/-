

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 传奇肛塞 extends Trinket {

	{
		image = 物品表.传奇肛塞;
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
					Messages.decimalFormat("#.##", 100*伏击()),
								Messages.decimalFormat("#.##",100*受伤()));
		} else {
			return Messages.get(this, "stats_desc",
					Messages.decimalFormat("#.##", 100*伏击(0)),
								Messages.decimalFormat("#.##",100*受伤(0)));
		}
	}

	public static float 伏击(){
		return 伏击(trinketLevel(传奇肛塞.class));
	}

	public static float 伏击(int level){
		if (level < 0){
			return 1;
		} else {
			return 1.1f + 0.05f*level;
		}
	}
	public static float 受伤(){
		return 受伤(trinketLevel(传奇肛塞.class));
	}

	public static float 受伤(int level){
		if (level < 0){
			return 1;
		} else {
			return 1.2f+0.1f*level;
		}
	}

}

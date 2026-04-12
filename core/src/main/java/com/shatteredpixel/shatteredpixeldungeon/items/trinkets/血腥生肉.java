

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血腥生肉 extends Trinket {

	{
		image = 物品表.血腥生肉;
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
								概率(),
								饥饿()*100
							   );
		} else {
			return Messages.get(this,"stats_desc",

								概率(0),
								饥饿(0)*100
							   );
		}
	}

	public static int 概率(){
		return 概率(trinketLevel(血腥生肉.class));
	}

	public static int 概率(int level){
		if (level < 0){
			return 0;
		} else {
			return 10-2*level;
		}
	}

	public static float 饥饿(){
		return 饥饿(trinketLevel(血腥生肉.class));
	}

	public static float 饥饿(int level){
		if (level < 0){
			return 1;
		} else {
			return 1.25f+0.25f*level;
		}
	}

}

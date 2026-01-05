

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 优惠卡 extends Trinket {

	{
		image = 物品表.优惠卡;
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
								打折(),
								获取()
							   );
		} else {
			return Messages.get(this, "stats_desc",
								打折(0),
								获取(0)
							   );
		}
	}

	public static int 打折(){
		return 打折(trinketLevel(优惠卡.class));
	}

	public static int 打折(int level){
		if (level < 0){
			return 10;
		} else {
			return 9-level;
		}
	}
	public static int 获取(){
		return 获取(trinketLevel(优惠卡.class));
	}

	public static int 获取(int level){
		if (level < 0){
			return 100;
		} else {
			return 95-5*level;
		}
	}

}

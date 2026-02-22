

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 狂妄皇冠 extends Trinket {

	{
		image = 物品表.CROWN;
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
								增加(),
								攻击()
							   );
		} else {
			return Messages.get(this,"stats_desc",

								增加(0),
								攻击(0)
							   );
		}
	}

	public static int 增加(){
		return 增加(trinketLevel(狂妄皇冠.class));
	}

	public static int 增加(int level){
		if (level < 0){
			return -1;
		} else {
			return 1+level;
		}
	}

	public static float 攻击(){
		return 攻击(trinketLevel(狂妄皇冠.class));
	}

	public static float 攻击(int level){
		if (level < 0){
			return 0;
		} else {
			return 0.1f-0.05f*level;
		}
	}

}

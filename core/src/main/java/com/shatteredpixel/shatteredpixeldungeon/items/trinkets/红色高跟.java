

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 红色高跟 extends Trinket {

	{
		image = 物品表.红色高跟;
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
								kw2(概率()),
								kw2(移速())
							   );
		} else {
			return Messages.get(this,"stats_desc",
								kw2(概率(0)),
								kw2(移速(0))
							   );
		}
	}


	public static int 概率(){
		return 概率(trinketLevel(红色高跟.class));
	}

	public static int 概率(int level){
		if (level < 0){
			return 0;
		} else {
			return 1+level;
		}
	}

	public static float 移速(){
		return 移速(trinketLevel(红色高跟.class));
	}

	public static float 移速(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.98f-0.02f*level;
		}
	}
}

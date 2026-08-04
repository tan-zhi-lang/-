

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 骸骨左轮 extends Trinket {

	{
		image = 物品表.骸骨左轮;
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
								kw2(伤害()*100),
									kw2(减少()*100)
							   );
		} else {
			return Messages.get(this,"stats_desc",

								kw2(伤害(0)*100),
									kw2(减少(0)*100)
							   );
		}
	}

	public static float 伤害(){
		return 伤害(trinketLevel(骸骨左轮.class));
	}

	public static float 伤害(int level){
		if (level < 0){
			return 0;
		} else {
			return 0.04f+0.02f*level;
		}
	}

	public static float 减少(){
		return 减少(trinketLevel(骸骨左轮.class));
	}

	public static float 减少(int level){
		if (level < 0){
			return 0;
		} else {
			return 0.04f-0.01f*level;
		}
	}

}

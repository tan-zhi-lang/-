

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 暗影飞刀 extends Trinket {

	{
		image = 物品表.暗影飞刀;
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
								kw2(伏击()),
									kw2(护甲()));
		} else {
			return Messages.get(this, "stats_desc",
								kw2(伏击(0)),
								kw2(护甲(0)));
		}
	}

	public static float 伏击(){
		return 伏击(trinketLevel(暗影飞刀.class));
	}

	public static float 伏击(int level){
		if (level < 0){
			return 1;
		} else {
			return 1.15f + 0.1f*level;
		}
	}
	public static float 护甲(){
		return 护甲(trinketLevel(暗影飞刀.class));
	}

	public static float 护甲(int level){
		if (level < 0){
			return 0;
		} else {
			return -10-5*level;
		}
	}

}

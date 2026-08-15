

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 本命玉佩 extends Trinket {

	{
		image = 物品表.本命玉佩;
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
								kw2((1-防御())*100),
									kw2(生命()*100));
		} else {
			return Messages.get(this,"stats_desc",
								kw2((1-防御(0))*100),
								kw2(生命(0)*100));
		}
	}

	public static float 防御(){
		return 防御(trinketLevel(本命玉佩.class));
	}

	public static float 防御(int level){
		if (level < 0){
			return 0;
		} else {
			return 0.75f-0.15f*level;
		}
	}
	public static float 生命(){
		return 生命(trinketLevel(本命玉佩.class));
	}

	public static float 生命(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.97f-0.04f*level;
		}
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 磨刀石 extends Trinket {

	{
		image = 物品表.磨刀石;
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
								Messages.decimalFormat("#.##",100*增加()),
								力量()
							   );
		} else {
			return Messages.get(this,"stats_desc",
								
								Messages.decimalFormat("#.##",100*增加(0)),
								力量(0)
							   );
		}
	}

	public static float 增加(){
		return 增加(trinketLevel(磨刀石.class));
	}

	public static float 增加(int level){
		if (level < 0){
			return 1;
		} else {
			return 1.1f+0.05f*level;
		}
	}

	public static float 力量(){
		return 力量(trinketLevel(磨刀石.class));
	}

	public static float 力量(int level){
		if (level < 0){
			return 0;
		} else {
			return 2+2*level;
		}
	}

}

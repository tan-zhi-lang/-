

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 虚无透纱 extends Trinket {

	{
		image = 物品表.虚无透纱;
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
								
								Messages.decimalFormat("#.##", 100*减少())
							   );
		} else {
			return Messages.get(this,"stats_desc",
								
								Messages.decimalFormat("#.##", 100*减少(0))
							   );
		}
	}

	public static float 增加(){
		return 增加(trinketLevel(虚无透纱.class));
	}

	public static float 增加(int level){
		if (level < 0){
			return 0;
		} else {
			return 2.5f+1.5f*level;
		}
	}

	public static float 减少(){
		return 减少(trinketLevel(虚无透纱.class));
	}

	public static float 减少(int level){
		if (level < 0){
			return 0;
		} else {
			return 0.1f-0.05f*level;
		}
	}

}

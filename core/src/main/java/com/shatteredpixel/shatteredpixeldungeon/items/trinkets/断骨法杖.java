

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 断骨法杖 extends Trinket {

	{
		image = 物品表.断骨法杖;
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
								Messages.decimalFormat("#.##",100*减少())
							   );
		} else {
			return Messages.get(this,"stats_desc",
								
								增加(0),
								Messages.decimalFormat("#.##",100*减少(0))
							   );
		}
	}

	public static int 增加(){
		return 增加(trinketLevel(断骨法杖.class));
	}

	public static int 增加(int level){
		if (level < 0){
			return 0;
		} else {
			return 1+level;
		}
	}

	public static float 减少(){
		return 减少(trinketLevel(断骨法杖.class));
	}

	public static float 减少(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.8f-0.1f*level;
		}
	}

}

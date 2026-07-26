

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 世界时表 extends Trinket {

	{
		image = 物品表.世界时表;
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
								时间()*100,
								移速()*100);
		} else {
			return Messages.get(this,"stats_desc",
								时间(0)*100,
								移速(0)*100);
		}
	}

	public static float 时间(){
		return 时间(trinketLevel(世界时表.class));
	}

	public static float 时间(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.95f-0.05f*level;
		}
	}
	public static float 移速(){
		return 移速(trinketLevel(世界时表.class));
	}

	public static float 移速(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.97f-0.04f*level;
		}
	}

}

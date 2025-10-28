

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 矛盾 extends Weapon{

	{
		image = 物品表.矛盾;
		
		hitSound = Assets.Sounds.HIT_STAB;
		
		
		tier = 1;
		间隔= 1.5f;
		伤害= 1.8f;
		范围 = 2;    //extra reach
		
		遗产= false;
	}
	
	@Override
	public int 力量(int lvl) {
		int req = 力量(tier, lvl)+1;
		if (masteryPotionBonus){
			req -= 2;
		}
		if (神力){
			req -= 2;
		}
		return req;
	}
	@Override
	public int 最大防御(int lvl){
		return 2 + lvl;
	}
	
}

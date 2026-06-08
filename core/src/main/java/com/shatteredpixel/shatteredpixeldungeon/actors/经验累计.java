package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.watabou.utils.Bundle;

public class 经验累计 extends CountBuff{
	float 经验;
	private static final String 经验x = "经验";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(经验x, 经验);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		经验 = bundle.getFloat(经验x);
	}
	public class 经验 extends CountBuff{}
	@Override
	public float set(float x){
		if(target instanceof Hero hero){

			if(hero.符文("海克斯获取:经验")){
				经验++;
				if(经验>=hero.升级所需(25)*6){
					经验=0;
					new 海克斯宝典().放背包();
				}
			}



		}
		return super.set(x);
	}
}
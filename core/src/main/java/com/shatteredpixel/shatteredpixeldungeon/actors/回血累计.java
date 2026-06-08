package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.watabou.utils.Bundle;

public class 回血累计 extends CountBuff{
	public float 恢复;
	private static final String 恢复x = "恢复";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(恢复x, 恢复);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		恢复 = bundle.getFloat(恢复x);
	}
	@Override
	public float set(float x){
		if(target instanceof Hero hero){

			if(hero.符文("海克斯获取:恢复")){
				恢复++;
				if(恢复>=hero.最大生命){
					恢复=0;
					new 海克斯宝典().放背包();
				}
			}



		}
		return super.set(x);
	}
}
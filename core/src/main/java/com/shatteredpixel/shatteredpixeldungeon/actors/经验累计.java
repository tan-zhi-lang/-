package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;

public class 经验累计 extends CountBuff{
	public class 经验 extends CountBuff{}
	@Override
	public float set(float x){
		if(target instanceof Hero hero){

			if(hero.符文("海克斯获取:经验")){
				Buff.施加(hero,经验.class).set(x);
				if(hero.buff(经验.class).count>=hero.升级所需(25)*6){
					hero.buff(经验.class).clearCount();
					new 海克斯宝典().放背包();
				}
			}



		}
		return super.set(x);
	}
}
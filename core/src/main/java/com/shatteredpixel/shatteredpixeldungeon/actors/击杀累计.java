package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;

public class 击杀累计 extends CountBuff{
	public class 屠杀 extends CountBuff{}
	@Override
	public float set(float x){
		if(target instanceof Hero hero){

			if(hero.符文("海克斯获取:屠杀")){
				Buff.施加(hero,屠杀.class).set(x);
				if(hero.buff(屠杀.class).count>=100){
					hero.buff(屠杀.class).clearCount();
					new 海克斯宝典().放背包();
				}
			}



		}
		return super.set(x);
	}
}
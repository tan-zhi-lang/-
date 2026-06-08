package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.watabou.utils.Bundle;

public class 击杀累计 extends CountBuff{
	public float 屠杀;
	public float 丘比;
	private static final String 屠杀x = "屠杀";
	private static final String 丘比x = "丘比";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(屠杀x, 屠杀);
		bundle.put(丘比x, 丘比);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		屠杀 = bundle.getFloat(屠杀x);
		丘比 = bundle.getFloat(丘比x);
	}
	@Override
	public float set(float x){
		if(target instanceof Hero hero){

			if(hero.符文("海克斯获取:屠杀")){
				屠杀++;
				if(屠杀>=125){
					屠杀=0;
					new 海克斯宝典().放背包();
				}
			}


			if(hero.符文("丘比")){
				if(丘比!=100)
					丘比++;
			}



		}
		return super.set(x);
	}
}
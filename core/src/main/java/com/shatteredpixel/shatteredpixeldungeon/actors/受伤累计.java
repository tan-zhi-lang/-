package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.watabou.utils.Bundle;

public class 受伤累计 extends CountBuff{
	public float 黄盖;
	public float 真血祭;
	private static final String 黄盖x = "黄盖";
	private static final String 真血祭x = "真血祭";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(黄盖x, 黄盖);
		bundle.put(真血祭x, 真血祭);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		黄盖 = bundle.getFloat(黄盖x);
		真血祭 = bundle.getFloat(真血祭x);
	}
	@Override
	public float set(float x){
		if(target instanceof Hero hero){

			if(hero.符文("三国杀:黄盖")){
				黄盖+=x;
				if(黄盖>=hero.最大生命(0.25f)){
					黄盖=0;
					Generator.随机物品().放背包();
				}
			}

			if(hero.符文("海克斯获取:真血祭")){
				真血祭+=x;
				if(真血祭>=hero.最大生命(2.25f)){
					真血祭=0;
					new 海克斯宝典().放背包();
				}
			}


		}
		return super.set(x);
	}
}
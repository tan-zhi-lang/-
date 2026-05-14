package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;

public class 受到伤害 extends CountBuff{
	public class 黄盖 extends CountBuff{}
	public class 真血祭 extends CountBuff{}
	@Override
	public float set(float x){
		if(target instanceof Hero hero){

			if(hero.符文("三国杀:黄盖")){
				Buff.施加(hero,黄盖.class).set(x);
				if(hero.buff(黄盖.class).count>=hero.最大生命(0.25f)){
					hero.buff(黄盖.class).clearCount();
					Generator.随机物品().放背包();
				}
			}

			if(hero.符文("海克斯获取:真血祭")){
				Buff.施加(hero,真血祭.class).set(x);
				if(hero.buff(真血祭.class).count>=hero.最大生命(1.75f)){
					hero.buff(真血祭.class).clearCount();
					new 海克斯宝典().放背包();
				}
			}


		}
		return super.set(x);
	}
}
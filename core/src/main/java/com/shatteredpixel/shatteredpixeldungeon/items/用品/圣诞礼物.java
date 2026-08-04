

package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圣诞礼物 extends 用品{
	
	
	{
		image = 物品表.圣诞礼物;
	}
	
	@Override
	public void 使用(Hero hero){
		Generator.随机物品().放背包();
		super.使用(hero);
	}
}

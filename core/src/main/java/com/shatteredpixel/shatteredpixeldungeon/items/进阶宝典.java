

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 进阶宝典 extends 用品 {
	
	
	{
		image = 物品表.MASTERY;
	}
	
	@Override
	public void 使用(Hero hero){
		hero.进阶=true;
		hero.更新生命();
		super.使用(hero);
	}

}

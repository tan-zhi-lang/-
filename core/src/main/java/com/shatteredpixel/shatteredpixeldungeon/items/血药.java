

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血药 extends 用品 {
	
	
	{
		image = 物品表.POTION_CRIMSON;
	}
	
	@Override
	public void 使用(Hero hero){
		hero.回百分比血(0.2f);
		super.使用(hero);
	}

}

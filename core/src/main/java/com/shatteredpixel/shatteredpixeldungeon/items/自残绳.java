

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 自残绳 extends 用品 {
	
	
	{
		image = 物品表.自残绳;
		遗产=false;
		红色=true;
	}
	
	@Override
	public void 使用(Hero hero){
		hero.死亡时(null);
		super.使用(hero);
	}
}

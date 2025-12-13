

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 进阶宝典 extends 用品 {
	
	
	{
		image = 物品表.MASTERY;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_USE)) {
			
			detach(hero.belongings.backpack);
			hero.进阶=true;
			hero.更新属性();
		}
	}
	

}

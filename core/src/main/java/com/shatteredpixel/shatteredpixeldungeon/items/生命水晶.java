

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 生命水晶 extends 用品 {
	
	
	{
		image = 物品表.生命水晶;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_USE)) {
			Sample.INSTANCE.play(Assets.Sounds.生命水晶);
			detach(hero.belongings.backpack);
			hero.血气+=4;
		}
	}
	

}

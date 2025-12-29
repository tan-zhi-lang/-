

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 圣诞礼物 extends 用品 {
	
	
	{
		image = 物品表.圣诞礼物;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_USE)) {
			
			hero.sprite.operate(hero.pos);
			hero.spend( 1f );
			hero.busy();
			Sample.INSTANCE.play(Assets.Sounds.生命水晶);
			detach(hero.belongings.backpack);
			Dungeon.level.drop(Generator.random(),curUser.pos).sprite().drop();
		}
	}
	

}

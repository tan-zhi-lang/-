

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 红包 extends 用品 {
	
	
	{
		image = 物品表.红包;
	}
	
	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.我恭喜你发财);

		if(hero.符文("红包"))
			new 属性锻造器().放背包();

		Dungeon.level.drop(new Gold().random(),hero.pos).sprite().drop();
		super.使用(hero);
	}

}

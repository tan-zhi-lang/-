

package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 生命果 extends 用品{
	
	
	{
		image = 物品表.生命果;
		黄色=true;
		黄光=true;
	}
	
	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.生命水晶);
		hero.根骨++;
		
		super.使用(hero);
	}

}

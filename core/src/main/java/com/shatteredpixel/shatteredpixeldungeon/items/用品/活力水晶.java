

package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 活力水晶 extends 用品{
	
	
	{
		image = 物品表.活力水晶;
		粉色=true;
		粉光=true;
	}
	
	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.生命水晶);
		hero.再生成长+=0.03f;
		
		super.使用(hero);
	}

}

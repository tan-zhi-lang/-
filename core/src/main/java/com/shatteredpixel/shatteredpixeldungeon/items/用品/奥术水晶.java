

package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 奥术水晶 extends 用品{


	{
		image = 物品表.奥术水晶;
		彩光 = true;
		结晶=true;
	}

	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.魔力水晶);
		hero.奥术=true;
		
		super.使用(hero);
	}

}

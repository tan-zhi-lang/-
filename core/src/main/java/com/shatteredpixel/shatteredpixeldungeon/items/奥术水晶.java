

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 奥术水晶 extends 用品 {


	{
		image = 物品表.奥术水晶;
	}

	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.魔力水晶);
		hero.奥术=true;
		hero.更新生命();
		super.使用(hero);
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WellFed;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 星之果实 extends 用品{
	
	
	{
		image = 物品表.星之果实;
		紫色=true;
	}
	
	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.星之果实);
		hero.回满血();
		hero.回满护甲();
		hero.生命成长+=25;
		hero.敏捷+=3;
		hero.buff(Hunger.class).吃饭(Hunger.STARVING+WellFed.上限());
		super.使用(hero);
	}

}

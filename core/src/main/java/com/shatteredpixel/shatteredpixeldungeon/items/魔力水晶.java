

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 魔力水晶 extends 用品 {
	
	
	{
		image = 物品表.魔力水晶;
	}
	
	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.魔力水晶);
		hero.智力++;

		super.使用(hero);
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{坠牢之星.class,};
			inQuantity = new int[]{5,};

			cost = 5;

			output = 魔力水晶.class;
			outQuantity = 1;
		}

	}
}

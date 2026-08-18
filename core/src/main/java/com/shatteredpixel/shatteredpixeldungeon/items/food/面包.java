

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 面包 extends Food {

	{
		image = 物品表.面包;
		energy = Hunger.HUNGRY;
		

	}

	@Override
	protected void eatSFX() {
		Sample.INSTANCE.play(Assets.Sounds.面包吃);
	}
	@Override
	public int 金币() {
		return 10 * quantity;
	}
}

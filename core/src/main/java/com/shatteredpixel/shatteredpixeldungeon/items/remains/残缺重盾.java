

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 残缺重盾 extends RemainsItem {

	{
		image = 物品表.残缺重盾;
	}

	@Override
	protected void doEffect(Hero hero) {
		Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
	}

}

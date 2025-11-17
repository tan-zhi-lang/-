

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class TornPage extends RemainsItem {

	{
		image = 物品表.TORN_PAGE;
	}

	@Override
	protected void doEffect(Hero hero) {
		int toHeal = Math.round(hero.最大生命 /10f);
		hero.回血(toHeal);
		Sample.INSTANCE.play( Assets.Sounds.READ );
	}

}

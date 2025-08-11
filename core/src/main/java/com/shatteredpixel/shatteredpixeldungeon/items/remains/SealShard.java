

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class SealShard extends RemainsItem {

	{
		image = 物品表.SEAL_SHARD;
	}

	@Override
	protected void doEffect(Hero hero) {
		Buff.施加(hero, Barrier.class).增加(Math.round(hero.最大生命 /5f));
		hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(Math.round(hero.最大生命 /5f)), FloatingText.SHIELDING );
		Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
	}

}

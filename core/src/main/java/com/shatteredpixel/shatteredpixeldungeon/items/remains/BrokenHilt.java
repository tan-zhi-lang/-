

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PhysicalEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

public class BrokenHilt extends RemainsItem {

	{
		image = ItemSpriteSheet.BROKEN_HILT;
	}

	@Override
	protected void doEffect(Hero hero) {
		Buff.施加( hero, PhysicalEmpower.class).set(Math.max(2, hero.等级 /3), 2);
		Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
	}
}
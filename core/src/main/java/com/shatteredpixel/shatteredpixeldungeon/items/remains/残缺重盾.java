

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyWard;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 残缺重盾 extends RemainsItem {

	{
		image = 物品表.残缺重盾;
	}

	@Override
	protected void doEffect(Hero hero) {
		Buff.施加(hero, HolyWard.HolyArmBuff.class, 50f);
		Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
	}

}

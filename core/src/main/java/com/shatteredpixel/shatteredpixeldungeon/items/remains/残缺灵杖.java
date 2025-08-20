

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class 残缺灵杖 extends RemainsItem {

	{
		image = 物品表.残缺灵杖;
	}

	@Override
	protected void doEffect(Hero hero) {
		hero.回血(hero.生命力(2));
		hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(hero.生命力(2)), FloatingText.SHIELDING );
		Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
	}

}

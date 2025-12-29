

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class BrokenStaff extends RemainsItem {

	{
		image = 物品表.BROKEN_STAFF;
	}

	@Override
	protected void doEffect(Hero hero) {
		hero.belongings.charge(1f);
		充能卷轴.charge(hero);
		Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );
	}

}

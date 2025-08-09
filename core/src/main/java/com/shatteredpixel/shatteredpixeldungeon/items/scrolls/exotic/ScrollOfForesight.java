

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Foresight;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;

public class ScrollOfForesight extends ExoticScroll {
	
	{
		icon = 物品表.Icons.SCROLL_FORESIGHT;
	}
	
	@Override
	public void doRead() {

		detach(curUser.belongings.backpack);
		Sample.INSTANCE.play( Assets.Sounds.READ );
		
		Buff.施加(curUser, Foresight.class, Foresight.DURATION);

		鉴定();
		
		readAnimation();
	}
	
}

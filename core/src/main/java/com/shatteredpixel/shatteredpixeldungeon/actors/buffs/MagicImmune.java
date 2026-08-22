

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.元素类型;
import com.watabou.noosa.Image;

public class MagicImmune extends FlavourBuff {

	public static final float DURATION = 20f;
	
	{
		type = buffType.POSITIVE;
		announced = true;
	}
	
	{
		免疫表.addAll(元素类型.RESISTS);
	}

	@Override
	public boolean attachTo(Char target) {
		if (super.attachTo(target)){
			for (Buff b : target.buffs()){
				for (Class immunity : 免疫表){
					if (b.getClass().isAssignableFrom(immunity)){
						b.detach();
						break;
					}
				}
			}
			if (target instanceof Hero){
//				((Hero) target).
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void detach() {
		super.detach();
		if (target instanceof Hero){
//			((Hero) target).
		}
	}

	@Override
	public int icon() {
		return BuffIndicator.COMBO;
	}
	
	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(0, 1, 0);
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}
}

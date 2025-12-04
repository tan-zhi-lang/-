

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class 手里剑 extends Weapon{

	{
		image = 物品表.SHURIKEN;
		hitSound = Assets.Sounds.HIT_STAB;
		
		延迟=0.8f;
		伤害=0.8f;
		伏击=0.67f;
		tier = 1;
	}
	
	@Override
	protected void onThrow(int cell) {
		super.onThrow(cell);
		if (curUser.buff(ShurikenInstantTracker.class) == null) {
			//1 less turn as the attack will be instant
			FlavourBuff.施加(curUser, ShurikenInstantTracker.class, ShurikenInstantTracker.DURATION-1);
		}
	}

	@Override
	public float castDelay(Char user, int cell) {
		return user.buff(ShurikenInstantTracker.class) != null ? super.castDelay(user, cell) : 0;
	}

	public static class ShurikenInstantTracker extends FlavourBuff {

		public static int DURATION = 20;

		@Override
		public int icon() {
			return BuffIndicator.THROWN_WEP;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0.6f, 0.6f, 0.6f);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

	}

}

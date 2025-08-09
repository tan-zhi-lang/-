

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class Shuriken extends MissileWeapon {

	{
		image = 物品表.SHURIKEN;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.2f;
		
		tier = 2;
		baseUses = 5;
	}
	
	@Override
	public int 最大攻击(int lvl) {
		return  4 * tier +                      //8 base, down from 10
				(tier == 1 ? 2*lvl : tier*lvl); //scaling unchanged
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

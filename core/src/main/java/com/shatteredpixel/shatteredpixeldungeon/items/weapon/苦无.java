

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class 苦无 extends Weapon{
	
	{
		image = 物品表.KUNAI;
		hitSound = Assets.Sounds.HIT_STAB;
		
		间隔=0.8f;
		伤害=0.8f;
		伏击=0.67f;
		tier = 3;
	}


	@Override
	protected void onThrow(int cell) {
		super.onThrow(cell);
		if (curUser.buff(苦无.KunaiInstantTracker.class)==null) {
			Badges.解锁女忍();
			//1 less turn as the attack will be instant
			FlavourBuff.施加(curUser,苦无.KunaiInstantTracker.class,苦无.KunaiInstantTracker.DURATION-1);
		}
	}

	@Override
	public float castDelay(Char user, int cell) {
		return user.buff(苦无.KunaiInstantTracker.class)!=null ? super.castDelay(user,cell) : 0;
	}
	public static class KunaiInstantTracker extends FlavourBuff {

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

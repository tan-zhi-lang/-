

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class 金玫苦无 extends Weapon{
	
	{
		image = 物品表.金玫苦无;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		特别= true;

		伤害=0.8f;
		伏击=0.67f;
		命中=0.85f;
		
		tier = 1;
	}
	@Override
	protected void onThrow(int cell) {
		super.onThrow(cell);
		if (curUser.buff(KunaiInstantTracker.class) == null) {
			//1 less turn as the attack will be instant
			FlavourBuff.施加(curUser,KunaiInstantTracker.class,KunaiInstantTracker.DURATION-1);
		}
	}
	
	@Override
	public float castDelay(Char user, int cell) {
		return user.buff(KunaiInstantTracker.class) != null ? super.castDelay(user, cell) : 0;
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

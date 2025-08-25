

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class Kunai extends MissileWeapon {
	
	{
		image = 物品表.KUNAI;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.1f;
		间隔=0.67f;
		tier = 3;
		baseUses = 5;
	}

	@Override
	public int 最大攻击(int lvl) {
		return  4 * tier +                      //12 base, down from 15
				(tier-1)*lvl;                   //scaling unchanged
	}
	
	@Override
	public int damageRoll(Char owner) {
		if (owner instanceof Hero) {
			Hero hero = (Hero)owner;
			Char enemy = hero.attackTarget();
			if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)) {
				//deals 60% toward max to max on surprise, instead of min to max.
				int diff = 最大攻击() - 最小攻击();
				int damage = augment.damageFactor(Hero.heroDamageIntRange(
						最小攻击() + Math.round(diff*0.6f),
						最大攻击()));
				int exStr = hero.力量() - 力量();
				if (exStr > 0) {
					damage += Hero.heroDamageIntRange(0, exStr);
				}
				return damage;
			}
		}
		return super.damageRoll(owner);
	}

	@Override
	protected void onThrow(int cell) {
		super.onThrow(cell);
		if (curUser.buff(Kunai.KunaiInstantTracker.class) == null) {
			//1 less turn as the attack will be instant
			FlavourBuff.施加(curUser, Kunai.KunaiInstantTracker.class, Kunai.KunaiInstantTracker.DURATION-1);
		}
	}

	@Override
	public float castDelay(Char user, int cell) {
		return user.buff(Kunai.KunaiInstantTracker.class) != null ? super.castDelay(user, cell) : 0;
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



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class Shuriken extends MissileWeapon {

	{
		image = 物品表.SHURIKEN;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.2f;
		命中=0.8f;
		间隔=0.6f;
		伤害=0.5f;
		伏击=true;
		伏击率=0.67f;
		tier = 2;
		baseUses = 5;
	}
	
	@Override
	public int 最大攻击(int lvl) {
		return  4 * tier + tier*lvl; //scaling unchanged
	}

	@Override
	public int damageRoll(Char owner) {
		if (owner instanceof Hero hero) {
			Char enemy = hero.attackTarget();
			if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)) {
				//deals 67% toward max to max on surprise, instead of min to max.
				int diff = 最大攻击() - 最小攻击();
				int damage = augment.damageFactor(Hero.heroDamageIntRange(
						最小攻击() + Math.round(diff*0.67f),
						最大攻击()));
					int exStr = hero.力量() - 力量();
					if (hero.heroClass(HeroClass.WARRIOR)) {
						if (exStr > 0) {
							damage += exStr;
						}
					}else{
						if (exStr > 0) {
							damage += Hero.heroDamageIntRange( 0, exStr );
						}
					}
				return damage;
			}
		}
		return super.damageRoll(owner);
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

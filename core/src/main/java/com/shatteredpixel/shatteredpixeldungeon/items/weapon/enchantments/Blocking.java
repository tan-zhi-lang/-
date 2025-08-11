

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HoldFast;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Blocking extends Weapon.Enchantment {
	
	private static ItemSprite.Glowing BLUE = new ItemSprite.Glowing( 0x0000FF );
	
	@Override
	public int proc(Weapon weapon, Char attacker, Char defender, int damage) {
		
		int level = Math.max( 0, weapon.强化等级() );

		// lvl 0 - 10%
		// lvl 1 ~ 12%
		// lvl 2 ~ 14%
		float procChance = (level+4f)/(level+40f) * procChanceMultiplier(attacker);
		if (Random.Float() < procChance){
			float powerMulti = Math.max(1f, procChance);

			BlockBuff b = Buff.施加(attacker, BlockBuff.class);
			int shield = Math.round(powerMulti * (2 + weapon.强化等级()));
			b.设置(shield);
			attacker.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(shield), FloatingText.SHIELDING);
			attacker.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 5);
		}
		
		return damage;
	}
	
	@Override
	public ItemSprite.Glowing glowing() {
		return BLUE;
	}

	public static class BlockBuff extends ShieldBuff {

		{
			type = buffType.POSITIVE;

			shieldUsePriority = 2;
		}

		private float left = 5f;

		@Override
		public boolean act() {
			left -= HoldFast.buffDecayFactor(target);
			if (left <= 0) {
				detach();
			} else {
				spend(TICK);
			}
			return true;
		}

		@Override
		public void 设置(int shield) {
			super.设置(shield);
			left = 5f;
		}

		@Override
		public void fx(boolean on) {
			if (on) {
				target.sprite.add(CharSprite.State.SHIELDED);
			} else if (target.buff(Barrier.class) == null) {
				target.sprite.remove(CharSprite.State.SHIELDED);
			}
		}

		@Override
		public int icon() {
			return BuffIndicator.ARMOR;
		}
		
		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0.5f, 1f, 2f);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (5f - left) / 5f);
		}

		@Override
		public String iconTextDisplay() {
			return Integer.toString((int)left);
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", 护盾量(), dispTurns(left));
		}

		public static String LEFT = "left";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(LEFT, left);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			left = bundle.getFloat(LEFT);
		}
	}
}

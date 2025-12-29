

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blocking;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class Barrier extends ShieldBuff {
	
	{
		type = buffType.POSITIVE;
	}

	float partialLostShield;

	@Override
	public void 增加(int amt) {
		super.增加(amt);
		partialLostShield = 0;
	}

	@Override
	public void 设置(int shield) {
		
		super.设置(shield);
		if (护盾量() == shield) partialLostShield = 0;
	}

	@Override
	public boolean act() {

		partialLostShield += Math.min(1f, 护盾量()/20f)
				* HoldFast.buffDecayFactor(target);

		if (partialLostShield >= 1f) {
			absorbDamage(1);
			partialLostShield = 0;
		}
		
		if (护盾量() <= 0){
			detach();
		}
		
		spend( TICK );
		
		return true;
	}
	
	@Override
	public void fx(boolean on) {
		if (on) {
			target.sprite.add(CharSprite.State.SHIELDED);
		} else if (target.buff(Blocking.BlockBuff.class) == null) {
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
	public String iconTextDisplay() {
		return Integer.toString(护盾量());
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc", 护盾量());
	}

	private static final String PARTIAL_LOST_SHIELD = "partial_lost_shield";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(PARTIAL_LOST_SHIELD, partialLostShield);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		partialLostShield = bundle.getFloat(PARTIAL_LOST_SHIELD);
	}
}

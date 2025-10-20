

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingHammer extends MissileWeapon {
	
	{
		image = 物品表.THROWING_HAMMER;
		hitSound = Assets.Sounds.HIT_CRUSH;
		hitSoundPitch = 0.8f;
		
		命中=0.9f;
		间隔=1.1f;
		伤害=1.2f;
		tier = 5;
		baseUses = 12;
		sticky = false;
	}
	public float pickupDelay() {
		return 0; //picked up instantly
	}
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		Buff.延长(defender, Vertigo.class, 1);
		return super.攻击时( attacker, defender, damage );
	}
}

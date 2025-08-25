

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Piranha;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class FishingSpear extends MissileWeapon {
	
	{
		image = 物品表.FISHING_SPEAR;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.1f;
		投矛=true;
		间隔=1.5f;
		tier = 2;
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage) {
		if (defender instanceof Piranha){
			damage = Math.max(damage, defender.生命 /2);
		}
		return super.攻击时(attacker, defender, damage);
	}
}

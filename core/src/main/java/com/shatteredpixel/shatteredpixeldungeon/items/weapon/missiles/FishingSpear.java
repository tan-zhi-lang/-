

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Piranha;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class FishingSpear extends MissileWeapon {
	
	{
		image = ItemSpriteSheet.FISHING_SPEAR;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.1f;
		
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

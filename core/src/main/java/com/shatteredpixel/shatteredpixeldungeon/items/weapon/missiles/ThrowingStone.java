

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingStone extends MissileWeapon {
	
	{
		image = 物品表.THROWING_STONE;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1.1f;
		
		bones = false;
		
		tier = 1;
		baseUses = 5;
		sticky = false;
	}
	
	@Override
	public int value() {
		return Math.round(super.value()/2f); //half normal value
	}
}

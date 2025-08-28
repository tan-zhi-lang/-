

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingStone extends MissileWeapon {
	
	{
		image = 物品表.THROWING_STONE;
		hitSoundPitch = 1.1f;
		
		bones = false;
		
		tier = 1;
		baseUses = 5;
		sticky = false;
	}
	
	@Override
	public int 金币() {
		return Math.round(super.金币()/2f); //half normal value
	}
}

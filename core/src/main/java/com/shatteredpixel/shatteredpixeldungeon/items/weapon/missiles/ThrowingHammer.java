

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingHammer extends MissileWeapon {
	
	{
		image = 物品表.THROWING_HAMMER;
		hitSound = Assets.Sounds.HIT_CRUSH;
		hitSoundPitch = 0.8f;
		
		tier = 5;
		baseUses = 12;
		sticky = false;
	}
	
	@Override
	public int max(int lvl) {
		return  4 * tier +                  //20 base, down from 25
				(tier) * lvl;               //scaling unchanged
	}
}

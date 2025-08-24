

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingSpear extends MissileWeapon {
	
	{
		image = 物品表.THROWING_SPEAR;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1f;
		投矛=true;
		延迟=1.5f;
		
		tier = 3;
	}
	
}

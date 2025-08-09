

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingSpike extends MissileWeapon {

	{
		image = 物品表.THROWING_SPIKE;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.2f;

		bones = false;

		baseUses = 12;
		tier = 1;
	}

}

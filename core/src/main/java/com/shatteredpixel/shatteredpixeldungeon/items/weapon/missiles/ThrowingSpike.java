

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingSpike extends MissileWeapon {

	{
		image = 物品表.THROWING_SPIKE;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.2f;

		bones = false;
		命中=1.3f;
		间隔=0.7f;
		伤害=0.6f;

		baseUses = 12;
		tier = 1;
	}

}

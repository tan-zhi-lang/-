

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingKnife extends MissileWeapon {
	
	{
		image = 物品表.THROWING_KNIFE;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.2f;
		命中=1.1f;
		间隔=0.9f;
		伤害=0.8f;
		伏击=true;
		伏击率=0.75f;
		bones = false;
		
		tier = 1;
		baseUses = 5;
	}
	
}

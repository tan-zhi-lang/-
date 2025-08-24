

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Trident extends MissileWeapon {
	
	{
		image = 物品表.TRIDENT;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 0.9f;
		投矛=true;
		延迟=1.5f;
		
		tier = 5;
	}
	
}

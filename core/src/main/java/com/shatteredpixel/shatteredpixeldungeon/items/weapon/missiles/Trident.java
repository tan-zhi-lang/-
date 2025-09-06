

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Trident extends MissileWeapon {
	
	{
		image = 物品表.TRIDENT;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 0.9f;
		投矛=true;
		命中=0.8f;
		间隔=1.3f;
		伤害=1.3f;
		
		tier = 5;
	}
	
}

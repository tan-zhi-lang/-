

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingSpear extends MissileWeapon {
	
	{
		image = 物品表.THROWING_SPEAR;
		hitSound = Assets.Sounds.HIT_STAB;
		
		投矛=true;
		命中=0.8f;
		间隔=1.3f;
		伤害=1.3f;
		
		tier = 3;
	}
	
}

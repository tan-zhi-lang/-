

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Javelin extends MissileWeapon {

	{
		image = 物品表.JAVELIN;
		hitSound = Assets.Sounds.HIT_STAB;
		
		投矛=true;
		间隔=1.5f;
		
		tier = 4;
	}
	
}

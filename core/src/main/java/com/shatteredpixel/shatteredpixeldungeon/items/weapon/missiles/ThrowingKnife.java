

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingKnife extends MissileWeapon {
	
	{
		image = 物品表.THROWING_KNIFE;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.2f;
		伏击=true;
		伏击率=0.75f;
		bones = false;
		
		tier = 1;
		baseUses = 5;
	}
	
	@Override
	public int 最大攻击(int lvl) {
		return  6 * tier +                      //6 base, up from 5
				(tier == 1 ? 2*lvl : tier*lvl); //scaling unchanged
	}
	
}

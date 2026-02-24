

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 短剑 extends Weapon{

	{
		image = 物品表.WORN_SHORTSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;

		tier = 1;
		
		技能=new 斩击();

	}


}

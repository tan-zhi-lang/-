

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨剑 extends Weapon{

	{
		image = 物品表.GREATSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 斩击();
		
		tier = 4;
		命中=1.1f;
	}

}

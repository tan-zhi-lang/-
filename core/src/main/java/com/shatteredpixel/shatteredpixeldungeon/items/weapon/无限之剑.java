

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 无限之剑 extends Weapon{

	{
		image = 物品表.无限之剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 斩击();
		
		tier = 5;
		延迟=0.6f;
	}

}

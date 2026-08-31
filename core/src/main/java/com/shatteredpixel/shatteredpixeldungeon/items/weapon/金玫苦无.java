

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 金玫苦无 extends Weapon{
	
	{
		image = 物品表.金玫苦无;
		hitSound = Assets.Sounds.攻击砍;
		
		特别= true;

		专属=true;
		延迟=0.8f;

		tier = 1;
	}

}

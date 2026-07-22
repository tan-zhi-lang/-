

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 誓刃 extends Weapon{
	
	{
		image = 物品表.誓刃;
		hitSound = Assets.Sounds.攻击砍;
		延迟=0.5f;
		流血=0.15f;
		麻痹=0.15f;
		tier = 5;
	}

}

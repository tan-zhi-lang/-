

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 回旋之刃 extends Weapon{
	{
		image = 物品表.回旋之刃;
		hitSound = Assets.Sounds.攻击砍;

		伤害=0.8f;
		特别=true;
		白色=true;
		tier=5;
	}

}

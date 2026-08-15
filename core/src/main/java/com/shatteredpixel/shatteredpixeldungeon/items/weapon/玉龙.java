

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 玉龙 extends Weapon {

	{
		image = 物品表.玉龙;
		hitSound = Assets.Sounds.攻击砍;
		伤害=0.8f;
		特别=true;
		冻结=0.15f;
		青色=true;
		tier = 5;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 寒冰鱼剑 extends Weapon {

	{
		image = 物品表.寒冰鱼剑;
		hitSound = Assets.Sounds.攻击砍;
		伤害=0.8f;
		特别=true;
		青色=true;
		tier = 5;
	}
}

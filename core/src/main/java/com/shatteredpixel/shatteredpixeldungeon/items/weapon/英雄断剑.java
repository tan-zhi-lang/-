

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 英雄断剑 extends Weapon{
	{
		image = 物品表.英雄断剑;
		hitSound = Assets.Sounds.HIT_SLASH;

		延迟=0.8f;
		伤害=0.8f;
		伏击=0.15f;
		特别=true;
		金币价值=2;
		遗产=false;
		tier=5;
	}
}

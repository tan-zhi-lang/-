

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 英雄断剑 extends Weapon{
	{
		image = 物品表.英雄断剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟=1.25f;
		伤害=0.85f;
		遗产=false;
		特别=true;
		tier=5;
	}
	@Override
	public int 金币() {
		return Math.round(super.金币()*6);
	}
}

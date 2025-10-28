

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 符文之刃 extends Weapon {

	{
		image = 物品表.RUNIC_BLADE;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier = 4;
	}
	@Override
	public int 强化等级(){
		return super.强化等级()+Math.round(0.25f*等级());
	}
}

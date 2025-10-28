

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 武士刀 extends Weapon {

	{
		image = 物品表.KATANA;
		hitSound = Assets.Sounds.HIT_SLASH;
		伤害=0.8f;
		tier = 4;
	}
	@Override
	public int 最大防御(int lvl){
		return 4 + lvl;
	}


}

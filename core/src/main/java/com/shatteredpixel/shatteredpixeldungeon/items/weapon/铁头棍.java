

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 铁头棍 extends Weapon {

	{
		image = 物品表.QUARTERSTAFF;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		
		延迟= 1.25f;
		伤害= 1.25f;
		tier = 1;
	}

	@Override
	public int 最大防御(int lvl){
		return 1 + lvl;
	}

}

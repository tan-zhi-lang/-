

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 暗杀之刃 extends Weapon {

	{
		image = 物品表.ASSASSINS_BLADE;
		hitSound = Assets.Sounds.HIT_STAB;
		
		伤害= 0.8f;
		
		伏击=0.5f;
		tier = 4;
	}


}
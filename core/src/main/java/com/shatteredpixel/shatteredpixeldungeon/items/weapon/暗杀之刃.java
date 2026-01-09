

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.潜行;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 暗杀之刃 extends Weapon {

	{
		image = 物品表.ASSASSINS_BLADE;
		hitSound = Assets.Sounds.HIT_STAB;

		延迟=0.9f;
		伤害= 0.7f;
		
		技能=new 潜行();
		伏击=1f;
		tier = 5;
	}


}
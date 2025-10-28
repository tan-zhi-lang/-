

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战斧 extends Weapon {

	{
		image = 物品表.BATTLE_AXE;
		hitSound = Assets.Sounds.HIT_SLASH;
		双手=true;
		间隔= 1.34f;
		伤害=1.5f;
		tier = 4;
	}

}

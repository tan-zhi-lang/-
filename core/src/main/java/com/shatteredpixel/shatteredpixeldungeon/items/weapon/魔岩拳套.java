

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 魔岩拳套 extends Weapon {
	
	{
		image = 物品表.GAUNTLETS;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		双手=true;
		拳套=true;
		tier = 5;
		间隔= 0.5f;
		伤害= 0.5f;
	}

}

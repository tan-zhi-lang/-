

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.无情铁手;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战斧 extends Weapon {

	{
		image = 物品表.BATTLE_AXE;
		hitSound = Assets.Sounds.HIT_SLASH;
		双手=true;
		延迟= 1.5f;
		伤害=1.25f;
		流血=0.25f;
		tier = 3;
		技能=new 无情铁手();
	}

}

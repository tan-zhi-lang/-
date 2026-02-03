

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.突刺;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 武士刀 extends Weapon {

	{
		image = 物品表.KATANA;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟=1.15f;
		伤害=0.85f;
		技能=new 突刺();
		tier = 2;
	}
	@Override
	public float 最大防御(int lvl){
		return 2 + lvl*2;
	}


}

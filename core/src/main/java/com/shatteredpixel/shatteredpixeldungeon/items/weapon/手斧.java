

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.劈斩;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 手斧 extends Weapon {

	{
		image = 物品表.HAND_AXE;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 劈斩();

		tier = 1;
		延迟=1.4f;
		伤害=1.2f;
		流血=0.35f;
	}
	

}

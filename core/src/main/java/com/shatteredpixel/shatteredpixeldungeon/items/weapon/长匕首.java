

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.背刺;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长匕首 extends Weapon{

	{
		image = 物品表.DIRK;
		hitSound = Assets.Sounds.HIT_STAB;

		延迟=0.8f;
		伤害= 0.8f;
		
		技能=new 背刺();

		tier = 2;
	}
	
}

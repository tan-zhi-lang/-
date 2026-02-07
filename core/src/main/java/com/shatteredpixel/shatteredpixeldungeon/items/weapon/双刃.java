

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.剑舞;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 双刃 extends Weapon{
	
	{
		image = 物品表.DAGGER;
		hitSound = Assets.Sounds.HIT_STAB;
		
		技能=new 剑舞();

		tier = 1;
		延迟=0.5f;
		伤害= 0.64f;
		双手=true;
		伏击=0.67f;
		遗产= false;
	}
}

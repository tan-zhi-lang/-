

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.剑舞;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 双剑 extends Weapon{

	{
		image = 物品表.SAI;
		hitSound = Assets.Sounds.HIT_STAB;
		
		
		技能=new 剑舞();
		
		tier = 3;
		延迟= 0.5f;
		伤害= 0.57f;
	}

}

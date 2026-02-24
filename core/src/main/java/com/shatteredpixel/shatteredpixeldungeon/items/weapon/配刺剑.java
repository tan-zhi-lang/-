

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.突刺;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 配刺剑 extends Weapon{

	{
		image = 物品表.RAPIER;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier = 1;
		延迟=0.85f;
		伤害= 0.8f;
		
		技能=new 突刺();

	}
	
	@Override
	public float 最大防御(int lvl){
		return 1 + lvl;
	}


}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 铁头棍 extends Weapon {

	{
		image = 物品表.QUARTERSTAFF;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		
		技能=new 防御姿态();
		伤害= 0.8f;
		tier = 1;
	}

	@Override
	public int 最大防御(int lvl){
		return 1 + lvl;
	}

}

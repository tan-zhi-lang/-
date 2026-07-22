

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.剑舞;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 双刃 extends Weapon{
	
	{
		image = 物品表.DAGGER;
		hitSound = Assets.Sounds.攻击刺;
		
		技能=new 剑舞();
		延迟=0.7f;
		tier = 1;
		

	}
}

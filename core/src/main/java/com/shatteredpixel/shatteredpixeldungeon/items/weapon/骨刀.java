

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.背刺;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 骨刀 extends Weapon{
	{
		image = 物品表.骨刀;
		hitSound = Assets.Sounds.攻击刺;

		延迟=0.8f;
		伏击=0.15f;

		技能=new 背刺();
		特别=true;
		白色=true;
		tier=5;
	}

}

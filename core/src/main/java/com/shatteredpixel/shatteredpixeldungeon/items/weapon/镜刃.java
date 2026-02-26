

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 镜刃 extends Weapon{

	{
		image = 物品表.镜刃;
		hitSound = Assets.Sounds.镜刃;
		
		伤害=0.85f;
		tier = 1;
		特别= true;

	}
	@Override
	public int 能量() {
		return Math.round(super.能量()*6);
	}

}

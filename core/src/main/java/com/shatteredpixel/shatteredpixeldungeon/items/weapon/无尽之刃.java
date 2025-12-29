

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.暴伤;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 无尽之刃 extends Weapon{
	{
		image = 物品表.无尽之刃;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟=1.15f;
		命中=0.85f;
		tier=5;
		技能=new 暴伤();
	}
}

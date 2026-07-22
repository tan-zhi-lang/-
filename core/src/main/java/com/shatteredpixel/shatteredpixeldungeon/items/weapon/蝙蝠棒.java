

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 蝙蝠棒 extends Weapon{
	{
		image = 物品表.蝙蝠棒;
		hitSound = Assets.Sounds.BAT;
		tier=5;
		伤害=0.6f;
		特别=true;
		靛色=true;
		吸血=0.15f;
	}
}

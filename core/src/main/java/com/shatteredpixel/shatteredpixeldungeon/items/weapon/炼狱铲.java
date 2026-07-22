

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 炼狱铲 extends Weapon{
	{
		image = 物品表.炼狱铲;
		hitSound = Assets.Sounds.棍棒;

		伤害=0.7f;
		特别=true;
		红色=true;
		tier=5;
		麻痹=0.15f;
	}

}

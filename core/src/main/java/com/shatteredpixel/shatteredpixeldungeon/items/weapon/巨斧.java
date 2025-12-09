

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.劈斩;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨斧 extends Weapon{

	{
		image = 物品表.巨斧;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟=1.35f;
		流血=0.2f;
		双手=true;
		技能=new 劈斩();

		tier = 4;
	}

}

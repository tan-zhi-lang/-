

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.劈斩;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 斩马刀 extends Weapon {

	{
		image = 物品表.斩马刀;
		hitSound = Assets.Sounds.HIT_SLASH;
		伤害=1.5f;
		延迟=1.5f;
		命中=0.85f;
		tier = 3;
		技能=new 劈斩();
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.嗜血;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 饮血之剑 extends Weapon{
	{
		image = 物品表.饮血之剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier=5;
		延迟=1.35f;
		吸血=0.15f;
		命中=0.8f;
		技能=new 嗜血();
	}
}

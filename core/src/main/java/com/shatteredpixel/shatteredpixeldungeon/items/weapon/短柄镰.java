

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.割草;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 短柄镰 extends Weapon {

	{
		image = 物品表.SICKLE;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 割草();
		tier = 1;
		延迟= 1.35f;
		伤害= 1.5f;
		命中=0.85f;
	}
}

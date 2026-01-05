

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.刺退;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 关刀 extends Weapon{

	{
		image = 物品表.GLAIVE;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 刺退();
		投矛=true;
		延迟=1.5f;
		伤害=1.5f;
		命中=0.7f;
		tier = 4;
		连招范围=2;
		范围 = 2;
	}
}

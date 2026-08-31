

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.破击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 小刺 extends Weapon{

	{
		image = 物品表.THROWING_SPIKE;
		hitSound = Assets.Sounds.攻击刺;

		延迟=0.8f;
		
		技能=new 破击();
		tier = 1;
	}

}

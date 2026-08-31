

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.破击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 双锥 extends Weapon{

	{
		image = 物品表.双锥;
		hitSound = Assets.Sounds.攻击刺;
		技能=new 破击();

		延迟=0.8f;
		
		tier = 3;
	}
}

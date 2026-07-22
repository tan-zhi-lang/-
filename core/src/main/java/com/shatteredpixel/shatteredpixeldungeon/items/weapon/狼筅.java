

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.刺退;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 狼筅 extends Weapon{
	
	{
		image = 物品表.狼筅;
		hitSound = Assets.Sounds.长枪;
		
		技能=new 刺退();
		延迟= 2f;
		范围=2;

		tier = 1;
	}

}

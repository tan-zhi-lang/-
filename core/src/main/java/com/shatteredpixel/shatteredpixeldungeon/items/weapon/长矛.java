

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.刺击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长矛 extends Weapon{

	{
		image = 物品表.SPEAR;
		hitSound = Assets.Sounds.长枪;
		
		技能=new 刺击();
		tier = 1;
		专属=true;
		特别=true;
		延迟= 1.5f;
//		连招范围=2;
		范围 = 2;
	}

}

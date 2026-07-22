

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.甩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 链枷 extends Weapon {

	{
		image = 物品表.FLAIL;
		hitSound = Assets.Sounds.锤打;
		
		技能=new 甩击();
		tier = 3;
//		连招范围=2;
		范围 = 2;
		延迟= 1.5f;
	}
	
	

}

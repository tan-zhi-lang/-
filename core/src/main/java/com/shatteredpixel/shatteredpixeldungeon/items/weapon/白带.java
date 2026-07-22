

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 白带 extends Weapon{

	{
		image = 物品表.白带;
		
		tier = 1;
		专属=true;
		
		延迟= 0.5f;
		魔法=0.15f;
		技能=new 连击();

	}

}

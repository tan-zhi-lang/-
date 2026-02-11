

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 镶钉手套 extends Weapon{

	{
		image = 物品表.GLOVES;
		
		技能=new 连击();
		tier = 1;
		延迟= 0.5f;
		伤害= 0.57f;

		遗产= false;
	}
}

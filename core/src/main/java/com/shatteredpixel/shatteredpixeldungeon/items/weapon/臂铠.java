

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 臂铠 extends Weapon{

	{
		image = 物品表.臂铠;

		tier = 1;
		延迟= 0.5f;
		专属=true;
		特别=true;
		技能=new 连击();

		具备防御=true;

	}
	

}

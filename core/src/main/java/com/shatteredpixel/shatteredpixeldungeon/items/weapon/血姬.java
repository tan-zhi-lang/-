

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血姬 extends Weapon{
	
	{
		image = 物品表.血姬;
		技能=new 连击();
		tier = 1;

		流血= 0.15f;
		专属=true;
		
		特别= true;
		红色= true;

	}
	
}

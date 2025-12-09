

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 白带 extends Weapon{

	{
		image = 物品表.白带;
		
		tier = 1;
		
		延迟= 0.43f;
		伤害= 0.5f;
		技能=new 连击();
		拳套=true;
		遗产= false;
	}

}

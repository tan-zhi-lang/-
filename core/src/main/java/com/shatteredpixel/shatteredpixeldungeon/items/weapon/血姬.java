

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血姬 extends Weapon{
	
	{
		image = 物品表.血姬;
		技能=new 连击();
		tier = 1;
		延迟= 0.75f;
		伤害= 0.5f;
		流血= 0.35f;
		拳套=true;
		伏击=0.85f;
		特别= true;
		遗产= false;
	}
	
}

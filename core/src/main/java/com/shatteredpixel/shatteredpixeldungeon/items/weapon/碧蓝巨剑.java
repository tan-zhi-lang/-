

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 碧蓝巨剑 extends Weapon{

	{
		image = 物品表.碧蓝巨剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		
		技能=new 斩击();
		tier = 1;
		延迟= 1.35f;
		伤害= 1.35f;
		遗产= false;
	}

}

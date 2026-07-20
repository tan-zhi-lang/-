

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.劈斩;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血砍刀 extends Weapon{
	
	{
		image = 物品表.血砍刀;
		hitSound = Assets.Sounds.HIT_STAB;
		tier = 1;
		延迟=0.15f;
		伤害=0.8f;
		流血=0.15f;
		技能=new 劈斩();
		
		特别= true;

		红色 = true;
	}
	
}

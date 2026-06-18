

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.突刺;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 配刺剑 extends Weapon{

	{
		image = 物品表.RAPIER;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier = 1;
		延迟=1.175f;
		
		技能=new 突刺();
		防御=true;

	}


}

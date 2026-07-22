

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.重击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 硬头锤 extends Weapon{

	{
		image = 物品表.MACE;
		hitSound = Assets.Sounds.锤打;
		
		技能=new 重击();

		tier = 2;
		延迟=1.175f;
		伤害=1.175f;
	}


}

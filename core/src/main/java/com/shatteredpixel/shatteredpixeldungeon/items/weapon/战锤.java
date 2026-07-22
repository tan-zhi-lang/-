

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.重击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战锤 extends Weapon{

	{
		image = 物品表.WAR_HAMMER;
		hitSound = Assets.Sounds.锤打;
		
		技能=new 重击();
		延迟=1.3f;
		
		tier = 4;
	}

}

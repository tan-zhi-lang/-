

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.重击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战锤 extends Weapon{

	{
		image = 物品表.WAR_HAMMER;
		hitSound = Assets.Sounds.锤打;
		伤害=0.7f;
		技能=new 重击();
		
		tier = 4;
	}

}

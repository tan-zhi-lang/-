

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战镰 extends Weapon{

	{
		image = 物品表.WAR_SCYTHE;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		
		双手=true;
		tier = 5;
		延迟= 1.5f;
		伤害= 1.75f;
	}



}

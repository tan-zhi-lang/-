

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.无情铁手;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战镰 extends Weapon{

	{
		image = 物品表.WAR_SCYTHE;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 无情铁手();
		
		tier = 5;
		延迟= 1.25f;
		伤害= 1.35f;
		命中=0.85f;
	}



}

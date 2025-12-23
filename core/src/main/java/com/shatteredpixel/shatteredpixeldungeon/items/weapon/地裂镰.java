

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.无情铁手;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 地裂镰 extends Weapon{

	{
		image = 物品表.地裂镰;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 无情铁手();
		双手=true;
		tier = 5;
		延迟= 1.35f;
		伤害= 1.5f;
		命中=0.75f;
	}



}

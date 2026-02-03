

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.立地;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 地裂镰 extends Weapon{

	{
		image = 物品表.地裂镰;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 立地();
		双手=true;
		tier = 5;
		延迟= 1.25f;
		伤害= 1.35f;
		命中=0.75f;
	}



}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长剑 extends Weapon{
	
	{
		image = 物品表.LONGSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 斩击();
		
		延迟= 1.25f;
		伤害= 1.25f;
		tier = 3;
		命中=1.15f;
	}

}

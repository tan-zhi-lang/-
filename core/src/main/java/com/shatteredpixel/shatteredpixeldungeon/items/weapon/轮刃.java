

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.大杀四方;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 轮刃 extends Weapon{
	
	{
		image = 物品表.BOOMERANG;
		hitSound = Assets.Sounds.HIT_CRUSH;
		伤害=0.68f;
		流血=0.35f;
		命中=0.85f;
		技能=new 大杀四方();
		tier = 4;
	}
}

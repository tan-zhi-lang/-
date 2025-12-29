

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.大杀四方;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 半月刃 extends Weapon{

	{
		image = 物品表.半月刃;
		hitSound = Assets.Sounds.HIT_SLASH;
		技能=new 大杀四方();
		tier = 3;
	}

}

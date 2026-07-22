

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.大杀四方;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 轮刃 extends Weapon{
	
	{
		image = 物品表.轮刃;
		hitSound = Assets.Sounds.锤打;
		伤害=0.8f;
		技能=new 大杀四方();
		tier = 4;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.无情铁手;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 链刃 extends Weapon{
	
	{
		image = 物品表.链刃;
		hitSound = Assets.Sounds.攻击刺;
		
		技能=new 无情铁手();

		伤害= 0.8f;
		范围 = 5;
		tier = 3;
	}

}

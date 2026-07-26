

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 指虎 extends Weapon{
	
	{
		image = 物品表.指虎;
		hitSound = Assets.Sounds.攻击刺;

		伤害=0.8f;
		流血=0.15f;
		
		技能=new 连击();

		tier = 1;
	}
	
}

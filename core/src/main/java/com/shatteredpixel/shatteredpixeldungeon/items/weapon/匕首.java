

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.潜行;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 匕首 extends Weapon{
	
	{
		image = 物品表.小匕;
		hitSound = Assets.Sounds.攻击刺;

		专属=true;
		特别=true;
		延迟=0.8f;
		
		技能=new 潜行();


		
		tier = 1;
	}
	
}

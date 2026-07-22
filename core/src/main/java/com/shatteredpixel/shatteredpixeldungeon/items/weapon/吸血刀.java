

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.嗜血;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 吸血刀 extends Weapon{
	
	{
		image = 物品表.吸血刀;
		hitSound = Assets.Sounds.攻击砍;
		
		红色 = true;
		特别= true;
		专属=true;

		延迟=0.8f;
		吸血=0.05f;
		技能=new 嗜血();
		
		tier = 1;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.破击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 雪球 extends Weapon{

	{
		image = 物品表.雪球;
		hitSound = Assets.Sounds.攻击刺;
		伤害=0.7f;
		冻结=0.15f;
		
		技能=new 破击();
		tier = 1;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.剑舞;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 弯刀 extends Weapon {

	{
		image = 物品表.SCIMITAR;
		hitSound = Assets.Sounds.攻击砍;
		
		技能=new 剑舞();

		tier = 2;
		伤害= 0.8f;
		流血=1.15f;
	}
}

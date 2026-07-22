

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.剑舞;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 妖刀村正 extends Weapon{

	{
		image = 物品表.妖刀村正;
		hitSound = Assets.Sounds.攻击砍;

		tier = 5;
		技能=new 剑舞();
		特别=true;
		蓝色=true;
		延迟= 0.8f;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 臭虎爪 extends Weapon{

	{
		image = 物品表.臭虎爪;
		hitSound = Assets.Sounds.爪;

		技能=new 连击();
		伤害=0.5f;
		延迟=0.25f;
		延迟自动转=false;
		特别=true;
		红色=true;
		tier = 5;
	}


}

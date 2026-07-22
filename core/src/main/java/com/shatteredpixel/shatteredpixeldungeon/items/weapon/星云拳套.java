

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 星云拳套 extends Weapon{
	{
		image = 物品表.星云拳套;
		hitSound = Assets.Sounds.攻击棍;
		技能=new 连击();
		延迟= 0.5f;
		特别=true;
		粉色=true;
		tier=5;
	}

}

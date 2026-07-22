

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.突刺;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 武士刀 extends Weapon {

	{
		image = 物品表.KATANA;
		hitSound = Assets.Sounds.巨剑;
		延迟=1.5f;
		技能=new 突刺();
		tier = 2;
		具备防御=true;
	}

}

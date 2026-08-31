

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 三节棍 extends Weapon {

	{
		image = 物品表.三节棍;
		hitSound = Assets.Sounds.棍棒;
		
		
		技能=new 防御姿态();

		延迟=0.5f;
		tier = 3;
		具备防御=true;
	}

}

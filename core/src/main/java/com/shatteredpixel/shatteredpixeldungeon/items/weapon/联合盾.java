

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 联合盾 extends Weapon{
	{
		image = 物品表.联合盾;
		hitSound = Assets.Sounds.盾牌;
		技能=new 防御姿态();
		伤害=0.7f;
		特别=true;
		红色=true;
		具备防御=true;
		防御=1.25f;
		tier=5;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.重击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 龙牙锤 extends Weapon{

	{
		image = 物品表.龙牙锤;
		hitSound = Assets.Sounds.锤打;
		伤害=0.7f;
		技能=new 重击();
		特别=true;
		紫色=true;
		tier = 5;
	}

}

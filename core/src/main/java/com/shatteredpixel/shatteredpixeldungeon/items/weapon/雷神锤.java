

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.重击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 雷神锤 extends Weapon{
	{
		image = 物品表.雷神锤;
		hitSound = Assets.Sounds.锤打;
		技能=new 重击();
		伤害=0.7f;
		魔法=0.3f;
		特别=true;
		白色=true;
		tier=5;
	}

}

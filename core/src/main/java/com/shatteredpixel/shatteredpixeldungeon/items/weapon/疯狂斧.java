

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.劈斩;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 疯狂斧 extends Weapon{
	{
		image = 物品表.疯狂斧;
		hitSound = Assets.Sounds.攻击砍;
		延迟=1.5f;
		技能=new 劈斩();
		特别=true;
		橙色=true;
		tier=5;
	}

}

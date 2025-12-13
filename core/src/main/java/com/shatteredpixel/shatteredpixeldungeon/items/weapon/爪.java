

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 爪 extends Weapon{

	{
		image = 物品表.爪;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		双手=true;
		拳套=true;
		技能=new 连击();
		
		延迟= 0.5f;
		伤害= 0.64f;
		tier = 2;
	}


}

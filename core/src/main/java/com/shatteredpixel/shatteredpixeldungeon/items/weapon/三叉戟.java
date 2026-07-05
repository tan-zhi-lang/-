

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.刺击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 三叉戟 extends Weapon{
	
	{
		image = 物品表.TRIDENT;
		hitSound = Assets.Sounds.HIT_STAB;
		
		技能=new 刺击();
		延迟= 1.34f;
		伤害= 1.34f;
//		连招范围=2;
		范围 = 2;
		
		tier = 4;
	}

}

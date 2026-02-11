

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 魔岩拳套 extends Weapon {
	
	{
		image = 物品表.GAUNTLETS;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		技能=new 连击();
		
		tier = 4;
		延迟= 0.5f;
		伤害= 0.5f;
	}

}

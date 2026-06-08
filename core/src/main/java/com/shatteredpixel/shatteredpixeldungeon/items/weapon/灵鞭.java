

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.群魔乱舞;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 灵鞭 extends Weapon{

	{
		image = 物品表.灵鞭;
		
		
		技能=new 群魔乱舞();
		tier = 1;

		伤害=0.7f;
		延迟= 1.175f;
		魔法=0.25f;
//		连招范围=3;
		范围 = 3;
		专属= true;
		特别= true;

	}
	@Override
	public int 强化等级(){
		int level = super.强化等级();
		if(Dungeon.hero())level+=Dungeon.hero.等级(0.2f)+Dungeon.hero.魔力(0.1f);
		return level;
	}
}

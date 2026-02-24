

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.大杀四方;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 灵鞭 extends Weapon{

	{
		image = 物品表.灵鞭;
		
		
		技能=new 大杀四方();
		tier = 1;

		伤害=0.75f;
		延迟= 1.25f;
		连招范围=3;
		范围 = 3;
		专属= true;
		特别= true;

	}
	@Override
	public int 强化等级(){
		return super.强化等级()+Dungeon.hero.等级(0.2f);
	}
}

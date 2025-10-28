

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 灵鞭 extends Weapon{

	{
		image = 物品表.灵鞭;
		

		tier = 1;
		
		间隔= 1.25f;
		伤害= 1.5f;
		范围 = 3;    //lots of extra reach
		
		特别= true;
		遗产= false;
	}
	@Override
	public int 最小攻击(int lvl) {
		int dmg =super.最小攻击(lvl) + Dungeon.hero.等级(Dungeon.hero.天赋点数(Talent.放逐之鞭,0.25f));
		return Math.max(0, dmg);
	}
	
	@Override
	public int 最大攻击(int lvl) {
		int dmg =super.最大攻击(lvl) + Dungeon.hero.等级(Dungeon.hero.天赋点数(Talent.放逐之鞭,0.5f));
		return Math.max(0, dmg);
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.大杀四方;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 灵鞭 extends Weapon{

	{
		image = 物品表.灵鞭;
		
		
		技能=new 大杀四方();
		tier = 1;
		
		延迟= 1.25f;
		连招范围=3;
		范围 = 3;
		
		特别= true;
		遗产= false;
	}
	@Override
	public int 最小攻击(int lvl) {
		int dmg =super.最小攻击(lvl) + Dungeon.hero.等级(Dungeon.hero.天赋点数(Talent.任督二脉,0.03f));
		return Math.max(0, dmg);
	}
	
	@Override
	public int 最大攻击(int lvl) {
		int dmg =super.最大攻击(lvl) + Dungeon.hero.等级(Dungeon.hero.天赋点数(Talent.任督二脉,0.3f));
		return Math.max(0, dmg);
	}
}

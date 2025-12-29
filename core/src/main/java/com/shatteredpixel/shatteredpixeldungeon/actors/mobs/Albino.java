

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.sprites.AlbinoSprite;
import com.watabou.utils.Random;

public class Albino extends Rat {

	{
		spriteClass = AlbinoSprite.class;
		
		生命 = 最大生命 = Dungeon.老鼠蝙蝠?60:12;
		经验 = Dungeon.老鼠蝙蝠?8:2;
		普通=false;
		
		loot = MysteryMeat.class;
		
		properties.add(Property.动物);
	}
	
	@Override
	public int 攻击时(Char enemy, int damage ) {
		damage = super.攻击时( enemy, damage );
		if (damage > 0 && Random.Int( 2 ) == 0) {
			int dmg= Random.NormalIntRange(Dungeon.老鼠蝙蝠?8:2, Dungeon.老鼠蝙蝠?12:3);
			dmg=Math.round(dmg*Dungeon.难度攻击());
			Buff.施加( enemy, 流血.class ).set(dmg);
		}
		
		return damage;
	}
	
	@Override
	public void 死亡时(Object cause){
		
		Badges.解锁鼠弟();
		super.死亡时(cause);
	}
}

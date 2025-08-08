

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GnollSprite;
import com.watabou.utils.Random;

public class Gnoll extends Mob {
	
	{
		spriteClass = GnollSprite.class;
		
		生命 = 最大生命 = 12;
		defenseSkill = 4;
		
		经验 = 2;
		最大等级 = 8;
		
		loot = Gold.class;
		lootChance = 0.5f;
	}
	
	@Override
	public int 攻击() {
		return Random.NormalIntRange( 1, 6 );
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 10;
	}
	
	@Override
	public int drRoll() {
		return super.drRoll() + Random.NormalIntRange(0, 2);
	}
}

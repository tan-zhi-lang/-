

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CausticSlimeSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class CausticSlime extends Slime {
	
	{
		spriteClass = CausticSlimeSprite.class;
		
		properties.add(Property.ACIDIC);
	}
	
	@Override
	public float 攻击时(final Char enemy, float damage ) {
		if (Random.Int( 2 ) == 0) {
			Buff.施加( enemy, Ooze.class ).set( Ooze.DURATION );
			enemy.sprite.burst( 0x000000, 5 );
		}
		
		return super.攻击时( enemy, damage );
	}
	
	@Override
	public void rollToDropLoot() {
		if (Dungeon.hero.等级 > 最大等级 + 2) return;
		
		super.rollToDropLoot();
		Dungeon.level.dropRandomCell( new GooBlob(), pos );
	}
}

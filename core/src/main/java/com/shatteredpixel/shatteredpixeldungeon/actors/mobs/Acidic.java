

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.AcidicSprite;

public class Acidic extends Scorpio {

	{
		spriteClass = AcidicSprite.class;
		
		properties.add(Property.ACIDIC);

		loot = 经验药剂.class;
		
		properties.add(Property.昆虫);
	}
	@Override
	public float 攻击时(final Char enemy, float damage) {
		Buff.施加(enemy, Ooze.class).set( Ooze.DURATION );
		return super.攻击时(enemy, damage);
	}

	@Override
	public float 防御时(Char enemy, float damage ) {
		if (Dungeon.level.adjacent(pos, enemy.pos)){
			Buff.施加(enemy, Ooze.class).set( Ooze.DURATION );
		}
		return super.防御时( enemy, damage );
	}

	@Override
	public Item createLoot() {
		return new 经验药剂();
	}
}

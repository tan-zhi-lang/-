

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
		lootChance = 1f;
	}
	@Override
	public int 攻击时(Char enemy, int damage) {
		Buff.施加(enemy, Ooze.class).set( Ooze.DURATION );
		return super.攻击时(enemy, damage);
	}

	@Override
	public int 防御时(Char enemy, int damage ) {
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

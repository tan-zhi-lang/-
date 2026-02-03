

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

public class Thorns extends Armor.Glyph {

	private static ItemSprite.Glowing RED = new ItemSprite.Glowing( 0x660022 );

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {

		int level = Math.max(0, armor.强化等级());

		// lvl 0 - 16.7%
		// lvl 1 - 23.1%
		// lvl 2 - 28.5%
		float procChance = (level+2f)/(level+12f) * procChanceMultiplier(defender);
		if ( attacker!=null&&attacker.alignment != defender.alignment && Random.Float() < procChance ) {

			float powerMulti = Math.max(1f, procChance);

			Buff.施加( attacker, 流血.class).set( Math.round((4 + level)*powerMulti) );

		}

		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return RED;
	}
}

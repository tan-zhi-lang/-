

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;

public class 晦暗 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		//no proc effect, triggered in Char.stealth()
		return damage;
	}

	public static float stealthBoost( Char owner, float level ){
		if (level == -1) {
			return 0;
		} else {
			return 2*genericProcChanceMultiplier(owner)
				   *Dungeon.hero.glyphLevel(臃肿.class);
		}
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;

public class 荆棘 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		if(attacker!=null){
			attacker.受伤时(damage*0.25f
							*procChanceMultiplier(defender)
							*defender.glyphLevel(荆棘.class));
		}
		return damage;
	}

}

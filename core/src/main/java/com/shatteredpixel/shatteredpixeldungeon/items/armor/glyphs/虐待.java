

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;

public class 虐待 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		if(defender!=null){
			if(defender.isAlive()){
				defender.回血(damage*0.08f
							  *procChanceMultiplier(defender)
							  *defender.glyphLevel(虐待.class));
			}
		}
		return damage;
	}
}

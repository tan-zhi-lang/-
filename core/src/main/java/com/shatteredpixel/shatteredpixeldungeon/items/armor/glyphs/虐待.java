

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;

public class 虐待 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		if(defender!=null){
			if(defender.isAlive()){
				defender.回血(damage*0.08f*procChanceMultiplier(defender));
			}
		}
		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return 深红;
	}
}

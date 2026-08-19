

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public class 荆棘 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		if(attacker!=null){
			attacker.受伤时(damage*0.3f
							*procChanceMultiplier(defender)
							*defender.glyphLevel(荆棘.class),Weapon.class);
		}
		return damage;
	}

}

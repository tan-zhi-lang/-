

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;

public class 荆棘 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		if(defender!=null){
			attacker.受伤时(damage*0.3f*procChanceMultiplier(defender),Weapon.class);
		}
		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return 棕;
	}
}



package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;

public class 位素 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		//no proc effect, triggers in Char.isImmune

		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return 紫;
	}

}

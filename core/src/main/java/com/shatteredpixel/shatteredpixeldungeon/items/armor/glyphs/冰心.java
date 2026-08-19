

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;

public class 冰心 extends Armor.Glyph {
	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {

		return damage;
	}

	public static float speedBoost(Char owner){
		if (Dungeon.hero==owner||Dungeon.hero.glyphLevel(冰心.class) == -1||Dungeon.hero.距离(owner)>2){
			return 1;
		}

		if (owner.sprite != null){
			owner.sprite.emitter().startDelayed(Speck.factory(Speck.BLUE_LIGHT),0.02f,5,0.05f);
		}
		return 0.8f / genericProcChanceMultiplier(Dungeon.hero)*Dungeon.hero.glyphLevel(冰心.class);

	}

}

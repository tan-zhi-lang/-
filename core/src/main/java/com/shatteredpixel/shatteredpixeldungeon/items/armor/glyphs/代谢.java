

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor.Glyph;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;

public class 代谢 extends Glyph {
	@Override
	public float proc( Armor armor, Char attacker, Char defender, float damage) {
		if(defender!=null){
			Hunger hunger=Buff.施加(defender,Hunger.class);

			hunger.吃饭(damage*0.3f*procChanceMultiplier(defender));

		}
		return damage;
	}

	@Override
	public Glowing glowing() {
		return 绿;
	}

}

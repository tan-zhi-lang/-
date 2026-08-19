

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor.Glyph;
import com.watabou.utils.Random;

public class 魅惑 extends Glyph {

	@Override
	public float proc( Armor armor, Char attacker, Char defender, float damage) {
		if(defender!=null){
			int level=Math.max(0,armor.强化等级());

			// lvl 0 - 15%
			// lvl 1 ~ 19%
			// lvl 2 ~ 23%
			float procChance=0.15f*procChanceMultiplier(defender)*defender.glyphLevel(魅惑.class);
			if(Random.Float()<procChance){

				float powerMulti=Math.max(1f,procChance);

				Buff.施加(attacker,Charm.class,Charm.DURATION*powerMulti).object=defender.id();
				attacker.sprite.centerEmitter().start(Speck.factory(Speck.HEART),0.2f,5);

			}
		}
		return damage;
	}


}



package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.EnergyParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;

public class 同位素 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		if(attacker!=null)
		if(defender instanceof Hero hero){
			int wands=hero.belongings.charge(0.1f
					 *procChanceMultiplier(hero)
						 *hero.glyphLevel(同位素.class));
			if(wands>0){
				hero.sprite.centerEmitter().burst(EnergyParticle.FACTORY,10);
			}
		}
		return damage;
	}
}

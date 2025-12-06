

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

public class Flow extends Armor.Glyph {

	private static ItemSprite.Glowing BLUE = new ItemSprite.Glowing( 0x0000FF );

	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		//no proc effect, triggers in Char.speed()
		return damage;
	}

	public static float speedBoost( Char owner, int level ){
		if (level == -1 || !Dungeon.level.water[owner.pos]){
			return 1;
		} else {
			if (owner.sprite != null){
				int particles = 2 + (int) Random.Float(1+level/2f);
				owner.sprite.emitter().startDelayed(Speck.factory(Speck.BLUE_LIGHT),0.02f,particles,0.05f);
			}
			return (2f + 0.5f*level) * genericProcChanceMultiplier(owner);
		}
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return BLUE;
	}

}

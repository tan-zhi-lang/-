

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Swiftness extends Armor.Glyph {

	private static ItemSprite.Glowing YELLOW = new ItemSprite.Glowing( 0xFFFF00 );

	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		//no proc effect, triggers in Char.speed()
		return damage;
	}

	public static float speedBoost( Char owner, int level ){
		if (level == -1){
			return 1;
		}

		boolean enemyNear = false;
		//an enemy counts as 'near' if they are within a 3-tile passable path of the hero
		//yes this does mean that things like visible trap tiles and chasms count as walls
		PathFinder.buildDistanceMap(owner.pos, Dungeon.level.passable, 3);
		for (Char ch : Actor.chars()){
			if (ch.alignment == Char.Alignment.ENEMY && PathFinder.distance[ch.pos] != Integer.MAX_VALUE){
				enemyNear=true;
			}
		}
		if (enemyNear){
			return 1;
		} else {
			if (owner.sprite != null){
				int particles = 1 + (int)Random.Float(1+level/5f);
				owner.sprite.emitter().startDelayed(Speck.factory(Speck.YELLOW_LIGHT),0.02f,particles,0.05f);
			}
			return (1.2f + 0.04f * level) * genericProcChanceMultiplier(owner);
		}
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return YELLOW;
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.armor.curses;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

public class Displacement extends Armor.Glyph {

	private static ItemSprite.Glowing BLACK = new ItemSprite.Glowing( 0x000000 );

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage ) {
		if(defender!=null){
			float procChance=1/20f*procChanceMultiplier(defender);
			if(Random.Float()<procChance){
				float powerMulti=Math.max(1f,procChance);
				Buff.施加(defender,Paralysis.class,4*powerMulti);
				传送卷轴.teleportChar(defender);
				return 0;
			}
		}
		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return BLACK;
	}

	@Override
	public boolean curse() {
		return true;
	}
}

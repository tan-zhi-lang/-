

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.watabou.noosa.audio.Sample;

public class 迷彩 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		//no proc effect, triggers in HighGrass.trample
		return damage;
	}

	public static void activate(Char ch, float level){
		if (level == -1) return;
		Buff.延长(ch, Invisibility.class, 3
										  * genericProcChanceMultiplier(ch)
			  *ch.glyphLevel(迷彩.class));
		if ( Dungeon.level.heroFOV[ch.pos] ) {
			Sample.INSTANCE.play( Assets.Sounds.MELD );
		}
	}


}


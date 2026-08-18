

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.GameMath;

public class 磐岩 extends Armor.Glyph {

	private static ItemSprite.Glowing GREY = new ItemSprite.Glowing( 0x222222 );

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		if(attacker!=null&&defender!=null){
			float accuracy=attacker.最小命中(defender)+attacker.最大命中(defender);
			float evasion=defender.最小闪避(attacker)+defender.最大闪避(attacker);
			float hitChance=1-evasion/accuracy/2f;

			hitChance=GameMath.之内(0.25f,hitChance*genericProcChanceMultiplier(defender),1f);

			damage*=hitChance;
		}
		return damage;
	}


	@Override
	public ItemSprite.Glowing glowing() {
		return GREY;
	}

}

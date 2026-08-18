

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;

public class 涌流 extends Armor.Glyph {

	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		//no proc effect, triggers in Char.speed()
		return damage;
	}

	public static float speedBoost( Char owner, int level ){
		if (level == -1 || !owner.在水中()){
			return 1;
		} else {
			if (owner.sprite != null){
				owner.sprite.emitter().startDelayed(Speck.factory(Speck.BLUE_LIGHT),0.02f,5,0.05f);
			}
			return 1.5f * genericProcChanceMultiplier(owner);
		}
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return 蓝;
	}

}

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;

public class 臃肿 extends Armor.Glyph{
		
		@Override
		public float proc(Armor armor,Char attacker,Char defender,float damage) {
			//no proc effect, triggers in Char.speed()
			return damage;
		}
	
	}
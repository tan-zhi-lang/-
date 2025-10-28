

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class AdrenalineDart extends TippedDart {
	
	{
		image = 物品表.ADRENALINE_DART;
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage) {

		if (processingChargedShot && defender == attacker) {
			//do nothing to the hero when processing charged shot
		} else if (attacker.alignment == defender.alignment){
			Buff.延长( defender, Adrenaline.class, Adrenaline.DURATION);
			return 0;
		} else {
			Buff.延长( defender, Cripple.class, Cripple.DURATION/2);
		}
		
		return super.攻击时(attacker, defender, damage);
	}
}

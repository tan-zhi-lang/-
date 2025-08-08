

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class ParalyticDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.PARALYTIC_DART;
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		//when processing charged shot, only stun enemies
		if (!processingChargedShot || attacker.alignment != defender.alignment) {
			Buff.延长(defender, Paralysis.class, 5f);
		}
		return super.攻击时( attacker, defender, damage );
	}
	
}

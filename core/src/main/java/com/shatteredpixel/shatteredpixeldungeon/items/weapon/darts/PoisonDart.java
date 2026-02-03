

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class PoisonDart extends TippedDart {
	
	{
		image = 物品表.POISON_DART;
	}
	
	@Override
	public float 攻击时(Char attacker, Char defender, float damage) {

		//when processing charged shot, only poison enemies
		if (!processingChargedShot || attacker.alignment != defender.alignment) {
			Buff.施加(defender, Poison.class).set(3 + Dungeon.scalingDepth() / 2);
		}
		
		return super.攻击时(attacker, defender, damage);
	}
}

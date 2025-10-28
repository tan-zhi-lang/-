

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;


public class RotDart extends TippedDart {
	
	{
		image = 物品表.ROT_DART;
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage) {

		//when processing charged shot, only corrode enemies
		if (processingChargedShot && attacker.alignment == defender.alignment) {
			//do nothing
		} else if (defender.properties().contains(Char.Property.BOSS)
				|| defender.properties().contains(Char.Property.MINIBOSS)){
			Buff.施加(defender, Corrosion.class).set(5f, Dungeon.scalingDepth()/3);
		} else {
			Buff.施加(defender, Corrosion.class).set(10f, Dungeon.scalingDepth());
		}
		
		return super.攻击时(attacker, defender, damage);
	}
	
}

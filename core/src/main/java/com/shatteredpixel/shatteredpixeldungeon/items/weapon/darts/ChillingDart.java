

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ChillingDart extends TippedDart {
	
	{
		image = 物品表.CHILLING_DART;
	}
	
	@Override
	public float 攻击时(Char attacker, Char defender, float damage) {

		if(defender!=null){
			//when processing charged shot, only chill enemies
			if(!processingChargedShot||attacker.alignment!=defender.alignment){
				if(Dungeon.level.water[defender.pos]){
					Buff.延长(defender,Chill.class,Chill.DURATION);
				}else{
					Buff.延长(defender,Chill.class,6f);
				}
			}
		}
		return super.攻击时(attacker, defender, damage);
	}
}

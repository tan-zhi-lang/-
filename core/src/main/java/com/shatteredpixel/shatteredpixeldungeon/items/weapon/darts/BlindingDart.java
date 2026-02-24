

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;


public class BlindingDart extends TippedDart {
	
	{
		image = 物品表.BLINDING_DART;
	}
	
	@Override
	public float 攻击时(Char attacker, Char defender, float damage) {
		if(defender!=null){
			//when processing charged shot, only blind enemies
			if(!processingChargedShot||attacker.alignment!=defender.alignment){
				Buff.施加(defender,Blindness.class,Blindness.DURATION);
			}
		}
		return super.攻击时(attacker, defender, damage);
	}
}

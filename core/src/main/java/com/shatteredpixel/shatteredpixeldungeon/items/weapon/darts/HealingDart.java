

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class HealingDart extends TippedDart {
	
	{
		image = 物品表.HEALING_DART;
		usesTargeting = false; //you never want to throw this at an enemy
	}
	
	@Override
	public float 攻击时(Char attacker, Char defender, float damage) {

		//do nothing to the hero or enemies when processing charged shot
		if (processingChargedShot && (defender == attacker || attacker.alignment != defender.alignment)){
			return super.攻击时(attacker, defender, damage);
		}
		
		//heals 30 hp at base, scaling with enemy HT
		治疗药剂.cure( defender );
		Buff.施加( defender, Healing.class ).setHeal((int)(0.5f*defender.最大生命 + 30), 0.25f, 0);
		
		if (attacker.alignment == defender.alignment){
			return 0;
		}
		
		return super.攻击时(attacker, defender, damage);
	}
	
}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class HealingDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.HEALING_DART;
		usesTargeting = false; //you never want to throw this at an enemy
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage) {

		//do nothing to the hero or enemies when processing charged shot
		if (processingChargedShot && (defender == attacker || attacker.alignment != defender.alignment)){
			return super.攻击时(attacker, defender, damage);
		}
		
		//heals 30 hp at base, scaling with enemy HT
		PotionOfHealing.cure( defender );
		Buff.施加( defender, Healing.class ).setHeal((int)(0.5f*defender.最大生命 + 30), 0.25f, 0);
		
		if (attacker.alignment == defender.alignment){
			return 0;
		}
		
		return super.攻击时(attacker, defender, damage);
	}
	
}

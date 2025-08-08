

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mimic;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.Random;

public class Vampiric extends Weapon.Enchantment {

	private static ItemSprite.Glowing RED = new ItemSprite.Glowing( 0x660022 );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		
		//chance to heal scales from 5%-30% based on missing HP
		float missingPercent = (attacker.最大生命 - attacker.生命) / (float)attacker.最大生命;
		float healChance = 0.05f + .25f*missingPercent;

		healChance *= procChanceMultiplier(attacker);
		
		if (Random.Float() < healChance
				&& attacker.alignment != defender.alignment
				&& (defender.alignment != Char.Alignment.NEUTRAL || defender instanceof Mimic)){

			float powerMulti = Math.max(1f, healChance);
			
			//heals for 50% of damage dealt
			int healAmt = Math.round(damage * 0.5f * powerMulti);
			healAmt = Math.min( healAmt, attacker.最大生命 - attacker.生命);
			
			if (healAmt > 0 && attacker.isAlive()) {
				
				attacker.生命 += healAmt;
				attacker.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString( healAmt ), FloatingText.HEALING );
				
			}
		}

		return damage;
	}
	
	@Override
	public Glowing glowing() {
		return RED;
	}
}

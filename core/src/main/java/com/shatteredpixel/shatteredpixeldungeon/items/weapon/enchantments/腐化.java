

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.watabou.utils.Random;

public class 腐化 extends Weapon.Enchantment {

	@Override
	public float proc(Weapon weapon, Char attacker, Char defender, float damage) {

		if(defender!=null){
			int level=Math.max(0,weapon.强化等级());

			// lvl 0 - 20%
			// lvl 1 ~ 23%
			// lvl 2 ~ 26%
			float procChance=0.15f*procChanceMultiplier(attacker);
			if(damage>=defender.生命&&Random.Float()<procChance&&!defender.免疫(Corruption.class)&&defender.buff(Corruption.class)==null&&defender instanceof Mob&&defender.isAlive()){

				Mob enemy=(Mob)defender;
				Hero hero=(attacker instanceof Hero)?
						(Hero)attacker:
						Dungeon.hero;

				Corruption.corruptionHeal(enemy);

				AllyBuff.affectAndLoot(enemy,hero,Corruption.class);

				return 0;
			}
		}
		return damage;
	}


}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public class 除魔 extends Weapon.Enchantment {

	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {
		if(defender!=null&&defender.恶魔亡灵())damage*=1.2f*procChanceMultiplier(attacker)*attacker.enchantmentlevel(除魔.class);
		return damage;
	}

}

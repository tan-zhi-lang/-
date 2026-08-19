package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public class 诡秘 extends Weapon.Enchantment {

	@Override
	public float proc(Weapon weapon, Char attacker, Char defender, float damage) {

		return damage;
	}

	}
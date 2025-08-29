

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Greatshield extends MeleeWeapon {

	{
		image = 物品表.GREATSHIELD;

		tier = 5;
	}

	@Override
	public int 最大攻击(int lvl) {
		return  Math.round(3f*(tier+1)) +   //18 base, down from 20
				lvl*(tier-1);               //+3 per level, down from +6
	}

	@Override
	public int 最大防御(int lvl){
		return 6 + 2*lvl;
	}
	
	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		RoundShield.guardAbility(hero, 3+ 强化等级(), this);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 3+ 强化等级());
		} else {
			return Messages.get(this, "typical_ability_desc", 3);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(3 + level);
	}
}

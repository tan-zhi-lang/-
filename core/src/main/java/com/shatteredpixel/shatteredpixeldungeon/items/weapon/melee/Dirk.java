

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Dirk extends MeleeWeapon {

	{
		image = 物品表.DIRK;
		hitSound = Assets.Sounds.HIT_STAB;
		命中= 1.3f;
		间隔= 0.7f;
		伤害= 0.6f;
		伏击=true;
		伏击率=0.67f;
		tier = 2;
	}
	
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	public boolean useTargeting(){
		return false;
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		双刃.sneakAbility(hero, target, 4, 2+ 强化等级(), this);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 2+ 强化等级());
		} else {
			return Messages.get(this, "typical_ability_desc", 2);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(2+level);
	}

}

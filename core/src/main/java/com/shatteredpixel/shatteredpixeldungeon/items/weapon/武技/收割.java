package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

public class 收割 extends 武技{
	/*
	
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void 使用武技(Hero hero,Integer target) {
		//replaces damage with 30+4.5*lvl bleed, roughly 133% avg base dmg, 129% avg scaling
		int bleedAmt = augment.damageFactor(Math.round(30f + 4.5f* 强化等级()));
		Sickle.harvestAbility(hero, target, 0f, bleedAmt, this);
	}

	@Override
	public String abilityInfo() {
		int bleedAmt = levelKnown ? Math.round(30f + 4.5f* 强化等级()) : 30;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(bleedAmt));
		} else {
			return Messages.get(this, "typical_ability_desc", bleedAmt);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(augment.damageFactor(Math.round(30f + 4.5f*level)));
	}
	 */
}

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

public class 架势 extends 武技{
	/*
	@Override
	protected void 使用武技(Hero hero,Integer target) {
		beforeAbilityUsed(hero, null);
		//1 turn less as using the ability is instant
		Buff.延长(hero, DefensiveStance.class, 2 + 强化等级());
		hero.sprite.operate();
		hero.next();
		afterAbilityUsed(hero);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 4+ 强化等级());
		} else {
			return Messages.get(this, "typical_ability_desc", 4);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(4+level);
	}

	public static class DefensiveStance extends FlavourBuff {

		{
			announced = true;
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.DUEL_EVASIVE;
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (4 - visualcooldown()) / 4);
		}
	}
	 */
}

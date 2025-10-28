package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

public class 穿刺 extends 武技{
	/*
	
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void 使用武技(Hero hero,Integer target) {
		if (target == null) {
			return;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(this, "ability_no_target"));
			return;
		}

		hero.belongings.abilityWeapon = this;
		if (!hero.canAttack(enemy)){
			GLog.w(Messages.get(this, "ability_target_range"));
			hero.belongings.abilityWeapon = null;
			return;
		}
		hero.belongings.abilityWeapon = null;

		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
				int damageBoost = 0;
				if (Char.hasProp(enemy, Char.Property.INORGANIC)
						|| enemy instanceof Swarm
						|| enemy instanceof Bee
						|| enemy instanceof Crab
						|| enemy instanceof Spinner
						|| enemy instanceof Scorpio) {
					//+(8+2*lvl) damage, equivalent to +100% damage
					damageBoost = augment.damageFactor(8 + 2* 强化等级());
				}
				beforeAbilityUsed(hero, enemy);
				AttackIndicator.target(enemy);
				if (hero.attack(enemy, 1, damageBoost, Char.INFINITE_ACCURACY)) {
					if (enemy.isAlive()) {
						Buff.施加(enemy, Vulnerable.class, 3f);
					} else {
						onAbilityKill(hero, enemy);
					}
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
				}
				Invisibility.notimedispel();
				hero.spendAndNext(hero.攻速());
				afterAbilityUsed(hero);
			}
		});
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = 8 + 2* 强化等级();
		return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 8 + 2*level;
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "~" + augment.damageFactor(最大攻击(level)+dmgBoost);
	}

	 */
}

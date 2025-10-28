package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

public class 刺退 extends 武技{
	/*
	ride
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void 使用武技(Hero hero,Integer target) {
		//+(9+2*lvl) damage, roughly +83% base damage, +80% scaling
		int dmgBoost = augment.damageFactor(9 + Math.round(2f* 强化等级()));
		Spear.spikeAbility(hero, target, 1, dmgBoost, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 9 + Math.round(2f* 强化等级()) : 9;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0)+dmgBoost, this.最大攻击(0)+dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 9 + Math.round(2f*level);
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "-" + augment.damageFactor(this.最大攻击(level)+dmgBoost);
	}

	public static void spikeAbility(Hero hero, Integer target, float dmgMulti, int dmgBoost, MeleeWeapon wep){
		if (target == null) {
			return;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(wep, "ability_no_target"));
			return;
		}

		hero.belongings.abilityWeapon = wep;
		if (!hero.canAttack(enemy) || Dungeon.level.adjacent(hero.pos, enemy.pos)){
			GLog.w(Messages.get(wep, "ability_target_range"));
			hero.belongings.abilityWeapon = null;
			return;
		}
		hero.belongings.abilityWeapon = null;

		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
				wep.beforeAbilityUsed(hero, enemy);
				AttackIndicator.target(enemy);
				int oldPos = enemy.pos;
				//do not push if enemy has moved, or another push is active (e.g. elastic)
				if (hero.attack(enemy, dmgMulti, dmgBoost, Char.INFINITE_ACCURACY)) {
					if (enemy.isAlive() && enemy.pos == oldPos && !Pushing.pushingExistsForChar(enemy)){
						//trace a ballistica to our target (which will also extend past them
						Ballistica trajectory = new Ballistica(hero.pos, enemy.pos, Ballistica.STOP_TARGET);
						//trim it to just be the part that goes past them
						trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);
						//knock them back along that ballistica
						WandOfBlastWave.throwChar(enemy, trajectory, 1, true, false, hero);
					} else if (!enemy.isAlive()) {
						wep.onAbilityKill(hero, enemy);
					}
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
				}
				Invisibility.notimedispel();
				hero.spendAndNext(hero.攻速());
				wep.afterAbilityUsed(hero);
			}
		});
	}

	 */
}

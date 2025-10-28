package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

public class 重击 extends 武技{
	/*
	
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		if(Random.Int(2)==0){
			Buff.延长(defender,Vertigo.class,1);
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void 使用武技(Hero hero,Integer target) {
		//+(5+1.5*lvl) damage, roughly +55% base dmg, +60% scaling
		int dmgBoost = augment.damageFactor(5 + Math.round(1.5f* 强化等级()));
		Mace.heavyBlowAbility(hero, target, 1, dmgBoost, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 5 + Math.round(1.5f* 强化等级()) : 5;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0)+dmgBoost, 最大攻击(0)+dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 5 + Math.round(1.5f*level);
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "-" + augment.damageFactor(最大攻击(level)+dmgBoost);
	}

	public static void heavyBlowAbility(Hero hero, Integer target, float dmgMulti, int dmgBoost, MeleeWeapon wep){
		if (target == null) {
			return;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(wep, "ability_no_target"));
			return;
		}

		hero.belongings.abilityWeapon = wep;
		if (!hero.canAttack(enemy)){
			GLog.w(Messages.get(wep, "ability_target_range"));
			hero.belongings.abilityWeapon = null;
			return;
		}
		hero.belongings.abilityWeapon = null;

		//no bonus damage if attack isn't a surprise
		if (enemy instanceof Mob && !((Mob) enemy).surprisedBy(hero)){
			dmgMulti = Math.min(1, dmgMulti);
			dmgBoost = 0;
		}

		float finalDmgMulti = dmgMulti;
		int finalDmgBoost = dmgBoost;
		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
				wep.beforeAbilityUsed(hero, enemy);
				AttackIndicator.target(enemy);
				if (hero.attack(enemy, finalDmgMulti, finalDmgBoost, Char.INFINITE_ACCURACY)) {
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
					if (enemy.isAlive()){
						Buff.施加(enemy, Daze.class, Daze.DURATION);
					} else {
						wep.onAbilityKill(hero, enemy);
					}
				}
				Invisibility.notimedispel();
				hero.spendAndNext(hero.攻速());
				wep.afterAbilityUsed(hero);
			}
		});
	}
	 */
}

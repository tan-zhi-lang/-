package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

public class 狠抽 extends 武技{
	/*
	

	@Override
	protected void 使用武技(Hero hero,Integer target) {

		ArrayList<Char> targets = new ArrayList<>();
		Char closest = null;

		hero.belongings.abilityWeapon = this;
		for (Char ch : Actor.chars()){
			if (ch.alignment == Char.Alignment.ENEMY
					&& !hero.isCharmedBy(ch)
					&& Dungeon.level.heroFOV[ch.pos]
					&& hero.canAttack(ch)){
				targets.add(ch);
				if (closest == null || Dungeon.level.trueDistance(hero.pos, closest.pos) > Dungeon.level.trueDistance(hero.pos, ch.pos)){
					closest = ch;
				}
			}
		}
		hero.belongings.abilityWeapon = null;

		if (targets.isEmpty()) {
			GLog.w(Messages.get(this, "ability_no_target"));
			return;
		}

		throwSound();
		Char finalClosest = closest;
		hero.sprite.attack(hero.pos, new Callback() {
			@Override
			public void call() {
				beforeAbilityUsed(hero, finalClosest);
				for (Char ch : targets) {
					//ability does no extra damage
					hero.attack(ch, 1, 0, Char.INFINITE_ACCURACY);
					if (!ch.isAlive()){
						onAbilityKill(hero, ch);
					}
				}
				Invisibility.notimedispel();
				hero.spendAndNext(hero.攻速());
				afterAbilityUsed(hero);
			}
		});
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()), augment.damageFactor(最大攻击()));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0), 最大攻击(0));
		}
	}

	public String upgradeAbilityStat(int level){
		return augment.damageFactor(最小攻击(level)) + "-" + augment.damageFactor(最大攻击(level));
	}
	 */
}

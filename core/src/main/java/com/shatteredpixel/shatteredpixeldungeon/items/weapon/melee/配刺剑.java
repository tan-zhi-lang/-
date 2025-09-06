

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class 配刺剑 extends MeleeWeapon {

	{
		image = 物品表.RAPIER;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.3f;

		tier = 1;
		最小 = 1;
		命中= 1.1f;
		间隔= 0.9f;
		伤害= 0.8f;

		bones = false;
	}
	
	@Override
	public int 最大防御(int lvl){
		return 1 + lvl;
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		//+(5+1.5*lvl) damage, roughly +111% base damage, +100% scaling
		int dmgBoost =  augment.damageFactor(6 + Math.round(1.75f* 强化等级()));
		lungeAbility(hero, target, 1, dmgBoost, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 5 + Math.round(1.5f* 强化等级()) : 5;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0)+dmgBoost, this.最大攻击(0)+dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 6 + Math.round(1.75f*level);
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "-" + augment.damageFactor(this.最大攻击(level)+dmgBoost);
	}

	public static void lungeAbility(Hero hero, Integer target, float dmgMulti, int dmgBoost, MeleeWeapon wep){
		if (target == null){
			return;
		}

		Char enemy = Actor.findChar(target);
		//duelist can lunge out of her FOV, but this wastes the ability instead of cancelling if there is no target
		if (Dungeon.level.heroFOV[target]) {
			if (enemy == null || enemy == hero || hero.isCharmedBy(enemy)) {
				GLog.w(Messages.get(wep, "ability_no_target"));
				return;
			}
		}

		if (hero.rooted || Dungeon.level.distance(hero.pos, target) < 2
				|| Dungeon.level.distance(hero.pos, target)-1 > wep.reachFactor(hero)){
			GLog.w(Messages.get(wep, "ability_target_range"));
			if (hero.rooted) PixelScene.shake( 1, 1f );
			return;
		}

		int lungeCell = -1;
		for (int i : PathFinder.NEIGHBOURS8){
			if (Dungeon.level.distance(hero.pos+i, target) <= wep.reachFactor(hero)
					&& Actor.findChar(hero.pos+i) == null
					&& (Dungeon.level.passable[hero.pos+i] || (Dungeon.level.avoid[hero.pos+i] && hero.flying))){
				if (lungeCell == -1 || Dungeon.level.trueDistance(hero.pos + i, target) < Dungeon.level.trueDistance(lungeCell, target)){
					lungeCell = hero.pos + i;
				}
			}
		}

		if (lungeCell == -1){
			GLog.w(Messages.get(wep, "ability_target_range"));
			return;
		}

		final int dest = lungeCell;

		hero.busy();
		Sample.INSTANCE.play(Assets.Sounds.MISS);
		hero.sprite.jump(hero.pos, dest, 0, 0.1f, new Callback() {
			@Override
			public void call() {
				if (Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR) {
					Door.leave( hero.pos );
				}
				hero.pos = dest;
				Dungeon.level.occupyCell(hero);
				Dungeon.observe();

				hero.belongings.abilityWeapon = wep; //set this early to we can check canAttack
				if (enemy != null && hero.canAttack(enemy)) {
					hero.sprite.attack(enemy.pos, new Callback() {
						@Override
						public void call() {

							wep.beforeAbilityUsed(hero, enemy);
							AttackIndicator.target(enemy);
							if (hero.attack(enemy, dmgMulti, dmgBoost, Char.INFINITE_ACCURACY)) {
								Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
								if (!enemy.isAlive()) {
									wep.onAbilityKill(hero, enemy);
								}
							}
							Invisibility.notimedispel();
							hero.spendAndNext(hero.攻速());
							wep.afterAbilityUsed(hero);
						}
					});
				} else {
					//spends charge but otherwise does not count as an ability use
					Charger charger = Buff.施加(hero, Charger.class);
					charger.partialCharge -= 1;
					while (charger.partialCharge < 0 && charger.charges > 0) {
						charger.charges--;
						charger.partialCharge++;
					}
					updateQuickslot();
					GLog.w(Messages.get(配刺剑.class, "ability_no_target"));
					hero.spendAndNext(1/hero.移速());
				}
			}
		});
	}
}

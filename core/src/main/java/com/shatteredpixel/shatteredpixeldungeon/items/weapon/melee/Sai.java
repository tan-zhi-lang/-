

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

public class Sai extends MeleeWeapon {

	{
		image = 物品表.SAI;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.3f;

		tier = 3;
		延迟 = 0.5f; //2x speed
	}

	@Override
	public int 最大攻击(int lvl) {
		return  Math.round(2.5f*(tier+1)) +     //10 base, down from 20
				lvl*Math.round(0.5f*(tier+1));  //+2 per level, down from +4
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		Sai.comboStrikeAbility(hero, target, 1f, 0, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 2 + 强化等级()/3 : 2;
		if (levelKnown){
			return Messages.get(this, "ability_desc", dmgBoost);
		} else {
			return Messages.get(this, "typical_ability_desc", dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		return "+" + augment.damageFactor(2 + level);
	}

	public static void comboStrikeAbility(Hero hero, Integer target, float multiPerHit, int boostPerHit, MeleeWeapon wep){
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

		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
				wep.beforeAbilityUsed(hero, enemy);
				AttackIndicator.target(enemy);

				hero.连击=2 + wep.强化等级()/3;
				boolean hit = hero.连击(enemy, multiPerHit, boostPerHit, hero.最大命中(0.5f));
				if (hit && !enemy.isAlive()){
					wep.onAbilityKill(hero, enemy);
				}

				Invisibility.dispel();
				hero.spendAndNext(hero.攻速());

				wep.afterAbilityUsed(hero);
			}
		});
	}

	public static class ComboStrikeTracker extends Buff {

		{
			type = buffType.POSITIVE;
		}

		public static int DURATION = 5;
		private float comboTime = 0f;
		public int hits = 0;

		@Override
		public int icon() {
			if (Dungeon.hero.belongings.weapon() instanceof 镶钉手套
					|| Dungeon.hero.belongings.weapon() instanceof Sai
					|| Dungeon.hero.belongings.weapon() instanceof Gauntlet
					|| Dungeon.hero.belongings.secondWep() instanceof 镶钉手套
					|| Dungeon.hero.belongings.secondWep() instanceof Sai
					|| Dungeon.hero.belongings.secondWep() instanceof Gauntlet) {
				return BuffIndicator.DUEL_COMBO;
			} else {
				return BuffIndicator.NONE;
			}
		}

		@Override
		public boolean act() {
			comboTime-=TICK;
			spend(TICK);
			if (comboTime <= 0) {
				detach();
			}
			return true;
		}

		public void addHit(){
			hits++;
			comboTime = 5f;

			if (hits >= 2 && icon() != BuffIndicator.NONE){
				GLog.p( Messages.get(Combo.class, "combo", hits) );
			}
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - comboTime)/ DURATION);
		}

		@Override
		public String iconTextDisplay() {
			return Integer.toString((int)comboTime);
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", hits, dispTurns(comboTime));
		}

		private static final String TIME  = "combo_time";
		public static String RECENT_HITS = "recent_hits";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(TIME, comboTime);
			bundle.put(RECENT_HITS, hits);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			if (bundle.contains(TIME)){
				comboTime = bundle.getInt(TIME);
				hits = bundle.getInt(RECENT_HITS);
			} else {
				//pre-2.4.0 saves
				comboTime = 5f;
				hits = 0;
				if (bundle.contains(RECENT_HITS)) {
					for (int i : bundle.getIntArray(RECENT_HITS)) {
						hits += i;
					}
				}
			}
		}
	}

}

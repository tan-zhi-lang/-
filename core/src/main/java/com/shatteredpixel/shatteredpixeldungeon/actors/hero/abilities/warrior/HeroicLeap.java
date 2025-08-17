

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class HeroicLeap extends ArmorAbility {

	{
		baseChargeUse = 35f;
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	public float chargeUse( Hero hero ) {
		float chargeUse = super.chargeUse(hero);
		if (hero.buff(DoubleJumpTracker.class) != null){
			//reduced charge use by 16%/30%/41%/50%
			chargeUse *= Math.pow(0.84, hero.天赋点数(Talent.DOUBLE_JUMP));
		}
		return chargeUse;
	}

	@Override
	public void activate( ClassArmor armor, Hero hero, Integer target ) {
		if (target != null) {

			if (hero.rooted){
				PixelScene.shake( 1, 1f );
				return;
			}

			Ballistica route = new Ballistica(hero.pos, target, Ballistica.STOP_TARGET | Ballistica.STOP_SOLID);
			int cell = route.collisionPos;

			//can't occupy the same cell as another char, so move back one.
			int backTrace = route.dist-1;
			while (Actor.findChar( cell ) != null && cell != hero.pos) {
				cell = route.path.get(backTrace);
				backTrace--;
			}

			armor.charge -= chargeUse( hero );
			armor.updateQuickslot();

			final int dest = cell;
			hero.busy();
			hero.sprite.jump(hero.pos, cell, new Callback() {
				@Override
				public void call() {
					hero.move(dest);
					Dungeon.level.occupyCell(hero);
					Dungeon.observe();
					GameScene.updateFog();

					for (int i : PathFinder.NEIGHBOURS8) {
						Char mob = Actor.findChar(hero.pos + i);
						if (mob != null && mob != hero && mob.alignment != Char.Alignment.ALLY) {
							if (hero.有天赋(Talent.BODY_SLAM)){
								int damage = Hero.heroDamageIntRange(hero.天赋点数(Talent.BODY_SLAM), 4*hero.天赋点数(Talent.BODY_SLAM));
								damage += Math.round(hero.防御()*0.25f*hero.天赋点数(Talent.BODY_SLAM));
								damage -= mob.防御();
								mob.受伤时(damage, hero);
							}
							if (mob.pos == hero.pos + i && hero.有天赋(Talent.IMPACT_WAVE)){
								Ballistica trajectory = new Ballistica(mob.pos, mob.pos + i, Ballistica.MAGIC_BOLT);
								int strength = 1+hero.天赋点数(Talent.IMPACT_WAVE);
								WandOfBlastWave.throwChar(mob, trajectory, strength, true, true, HeroicLeap.this);
								if (Random.Int(4) < hero.天赋点数(Talent.IMPACT_WAVE)){
									Buff.延长(mob, Vulnerable.class, 5f);
								}
							}
						}
					}

					WandOfBlastWave.BlastWave.blast(dest);
					PixelScene.shake(2, 0.5f);

					Invisibility.dispel();
					hero.spendAndNext(Actor.TICK);

					if (hero.buff(DoubleJumpTracker.class) != null){
						hero.buff(DoubleJumpTracker.class).detach();
					} else {
						if (hero.有天赋(Talent.DOUBLE_JUMP)) {
							Buff.施加(hero, DoubleJumpTracker.class, 3);
						}
					}
				}
			});
		}
	}

	public static class DoubleJumpTracker extends FlavourBuff{};

	@Override
	public int icon() {
		return HeroIcon.HEROIC_LEAP;
	}

	@Override
	public Talent[] talents() {
		return new Talent[]{Talent.BODY_SLAM, Talent.IMPACT_WAVE, Talent.DOUBLE_JUMP, Talent.HEROIC_ENERGY};
	}
}

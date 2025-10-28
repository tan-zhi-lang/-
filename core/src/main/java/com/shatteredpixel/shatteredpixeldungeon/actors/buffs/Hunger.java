

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

public class Hunger extends Buff implements Hero.Doom {

	public static final float HUNGRY	= 300f;
	public static final float STARVING	= 450f;

	private float level;
	private float partial=1;

	private static final String LEVEL			= "level";
	private static final String PARTIAL 	= "partial";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( LEVEL, level );
		bundle.put( PARTIAL, partial );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		level = bundle.getFloat( LEVEL );
		partial = bundle.getFloat(PARTIAL);
	}

	@Override
	public boolean act() {

		if (Dungeon.level.locked
				|| target.buff(WellFed.class) != null
				|| SPDSettings.intro()
				|| target.buff(ScrollOfChallenge.ChallengeArena.class) != null){
			spend(TICK);
			return true;
		}
		if(target instanceof Hero hero&&(hero.heroClass(HeroClass.机器)||hero.heroClass(HeroClass.凌云))){
			spend(TICK);
			return true;
		}

		if (target.isAlive() && target instanceof Hero hero) {
			
			if (level > HUNGRY&&hero.heroClass(HeroClass.罗兰)) {
				if(hero.nobuff(Vulnerable.class)){
				Buff.延长(hero, Vulnerable.class, 10);
				}
				if(hero.nobuff(Weakness.class)){
				Buff.延长(hero, Weakness.class, 10);
				}
				
			}
			if (isStarving()) {
				
				partial++;

				if (partial >= 10){
					target.受伤时( target.生命力(0.13f), this);
					partial=1;
				}
				
			} else {

				float hungerDelay = 1f;
				if (target.buff(Shadows.class) != null){
					hungerDelay *= 1.5f;
				}
				hungerDelay /= SaltCube.hungerGainMultiplier();

				float newLevel = level + (1f/hungerDelay);
				if (newLevel >= STARVING) {

					GLog.n( Messages.get(this, "onstarving") );
					hero.受伤时( 1, this );

					hero.interrupt();
					newLevel = STARVING;

				} else if (newLevel >= HUNGRY && level < HUNGRY) {

					GLog.w( Messages.get(this, "onhungry") );

					if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_FOOD)){
						GameScene.flashForDocument(Document.ADVENTURERS_GUIDE, Document.GUIDE_FOOD);
					}

				}
				level = newLevel;

			}
			spend( TICK );

		} else {

			diactivate();

		}

		return true;
	}

	public void 吃饭(float energy ) {
		affectHunger( energy, false );
	}
	public void affectHunger(float energy, boolean overrideLimits ) {
		if(target instanceof Hero hero){
			hero.吃饭触发+=energy;
			hero.污蔑狂宴+=energy;
			
			Talent.吃饭时(hero,energy/150f);
		}
		if (energy < 0 && target.buff(WellFed.class) != null){
			target.buff(WellFed.class).left += energy;
			BuffIndicator.refreshHero();
			return;
		}

		float oldLevel = level;

		level -= energy;
		if (level < 0 && !overrideLimits) {
			level = 0;
		} else if (level > STARVING) {
			level = STARVING;
			target.受伤时( target.生命力(0.18f), this );
		}

		if (oldLevel < HUNGRY && level >= HUNGRY){
			GLog.w( Messages.get(this, "onhungry") );
		} else if (oldLevel < STARVING && level >= STARVING){
			GLog.n( Messages.get(this, "onstarving") );
			target.受伤时( 1, this );
		}

		BuffIndicator.refreshHero();
	}

	public boolean isStarving() {
		return level >= STARVING;
	}

	public int hunger() {
		return (int)Math.ceil(level);
	}

//	@Override
//	public int icon() {
//		if (level < HUNGRY) {
//			return BuffIndicator.NONE;
//		} else if (level < STARVING) {
//			return BuffIndicator.HUNGER;
//		} else {
//			return BuffIndicator.STARVATION;
//		}
//	}

	@Override
	public String name() {
		if (level < STARVING) {
			return Messages.get(this, "hungry");
		} else {
			return Messages.get(this, "starving");
		}
	}

	@Override
	public String desc() {
		String result;
		if (level < STARVING) {
			result = Messages.get(this, "desc_intro_hungry");
		} else {
			result = Messages.get(this, "desc_intro_starving");
		}

		result += Messages.get(this, "desc");

		return result;
	}

	@Override
	public void onDeath() {

		Badges.validateDeathFromHunger();

		Dungeon.fail( this );
		GLog.n( Messages.get(this, "ondeath") );
	}
}

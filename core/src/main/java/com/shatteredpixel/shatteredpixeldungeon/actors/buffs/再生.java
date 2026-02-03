

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.SpiritForm;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.恢复之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ChaoticCenser;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.levels.VaultLevel;
import com.watabou.utils.Bundle;

public class 再生 extends Buff {
	
	{
		//unlike other buffs, this one acts after the hero and takes priority against other effects
		//healing is much more useful if you get some of it off before taking damage
		actPriority = HERO_PRIO - 1;
	}

	public float partialRegen = 0f;
	
	private static final float REGENERATION_DELAY = 10; //1HP every 10 turns
	
	@Override
	public boolean attachTo( Char target) {
		if (super.attachTo( target )) {
			//if we're loading in and the hero has partially spent a turn, delay for 1 turn
			if (target instanceof Hero && Dungeon.hero == null && cooldown() == 0 && target.cooldown() > 0) {
				spend(TICK);
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean act() {
		if (target.isAlive()) {
		
			//if other trinkets ever get buffs like this should probably make the buff attaching
			// behaviour more like wands/rings/artifacts
			if (ChaoticCenser.averageTurnsUntilGas() != -1){
				Buff.施加(Dungeon.hero, ChaoticCenser.CenserGasTracker.class);
			}

			if (regenOn() && !target.满血() && !((Hero)target).isStarving()) {
		boolean chaliceCursed = false;
		int chaliceLevel = -1;
		if (target.buff(MagicImmune.class) == null) {
			if (Dungeon.hero.buff(ChaliceOfBlood.chaliceRegen.class) != null) {
				chaliceCursed = Dungeon.hero.buff(ChaliceOfBlood.chaliceRegen.class).isCursed();
				chaliceLevel = Dungeon.hero.buff(ChaliceOfBlood.chaliceRegen.class).itemLevel();
			} else if (Dungeon.hero.buff(SpiritForm.SpiritFormBuff.class) != null
					   && Dungeon.hero.buff(SpiritForm.SpiritFormBuff.class).artifact() instanceof ChaliceOfBlood) {
				chaliceLevel = SpiritForm.artifactLevel();
			}
		}

				float 延迟= REGENERATION_DELAY;
			if (chaliceLevel != -1 && target.buff(MagicImmune.class) == null) {
				if (chaliceCursed) {
					延迟*= 1.5f;
				} else {
					//15% boost at +0, scaling to a 500% boost at +10
					延迟-=1.33f+chaliceLevel*0.667f;
					延迟/= 能量之戒.artifactChargeMultiplier(target);
				}
			}
		
			//salt cube is turned off while regen is disabled.
			if (target.buff(LockedFloor.class) == null) {
				延迟/= SaltCube.healthRegenMultiplier();
			}

				partialRegen =1f/延迟*恢复之戒.恢复(target);

				if (partialRegen > 0) {
					if(target instanceof Hero hero){
						
						if(!(hero.heroClass(HeroClass.机器)||hero.heroClass(HeroClass.凌云))){
							float x=partialRegen-(hero.heroClass(HeroClass.血鬼)?0.5f:0);
							hero.回血(x);
						}
//						partialRegen=0;
						if (hero.满血()) {
							hero.resting = false;
						}
					}
				}
			}

			spend( TICK );
			
		} else {
			
			diactivate();
			
		}
		
		return true;
	}

	public static boolean regenOn(){
		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (lock != null && !lock.regenOn()){
			return false;
		}
		if (Dungeon.level instanceof VaultLevel){
			return false;
		}
		return true;
	}

	public static final String PARTIAL_REGEN = "partial_regen";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(PARTIAL_REGEN, partialRegen);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		partialRegen = bundle.getFloat(PARTIAL_REGEN);
	}
}

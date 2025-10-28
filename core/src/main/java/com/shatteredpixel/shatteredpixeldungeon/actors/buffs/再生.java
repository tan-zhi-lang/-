

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.SpiritForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.道术;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ChaoticCenser;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.utils.Bundle;

public class 再生 extends Buff implements ActionIndicator.Action {
	
	{
		//unlike other buffs, this one acts after the hero and takes priority against other effects
		//healing is much more useful if you get some of it off before taking damage
		actPriority = HERO_PRIO - 1;
	}

	private float partialRegen = 0f;

	private static final float REGENERATION_DELAY = 10; //1HP every 10 turns
	
	
	public 道术 targetingSpell = null;
	
	public 道术 quickSpell = null;
//	@Override
//	public String actionName() {
//		return "技能";
//	}
//
//	@Override
//	public int actionIcon() {
//		return HeroIcon.NONE;
//	}
	@Override
	public String actionName() {
		return quickSpell.name();
	}
	
	@Override
	public int actionIcon() {
		return quickSpell.icon()+8;
	}
	@Override
	public Visual primaryVisual() {
		Image ico;
		if (Dungeon.hero.belongings.weapon == null){
			ico = new HeroIcon(this);
		} else {
			ico = new ItemSprite(Dungeon.hero.belongings.weapon);
		}
		ico.width += 4; //shift slightly to the left to separate from smaller icon
		return ico;
	}
	
	@Override
	public Visual secondaryVisual() {
		Image ico;
		if (Dungeon.hero.belongings.secondWep == null){
			ico = new HeroIcon(this);
		} else {
			ico = new ItemSprite(Dungeon.hero.belongings.secondWep);
		}
		ico.scale.set(PixelScene.align(0.51f));
		ico.brightness(0.6f);
		return ico;
	}
	
	
	public void setQuickSpell(道术 spell){
		if (quickSpell == spell){
			quickSpell = null; //re-assigning the same spell clears the quick spell
			if (this != null){
				ActionIndicator.clearAction(this);
			}
		} else {
			quickSpell = spell;
			if (this!= null){
				ActionIndicator.setAction(this);
			}
		}
	}
	public boolean canCast( Hero hero, 道术 spell){
		return hero.buff(MagicImmune.class)==null
			   && hero.法力>=10
			   && spell.canCast(hero);
	}
	
	@Override
	public int indicatorColor() {
		return 0x5500BB;
	}
	@Override
	public boolean attachTo( Char target) {
		if (super.attachTo( target )) {
			//if we're loading in and the hero has partially spent a turn, delay for 1 turn
			if (target instanceof Hero && Dungeon.hero == null && cooldown() == 0 && target.cooldown() > 0) {
				spend(TICK);
			}
			if (quickSpell != null) ActionIndicator.setAction(this);
			return true;
		}
		return false;
	}
	@Override
	public void doAction() {
//		if (!canCast(Dungeon.hero, quickSpell)){
//			GLog.w(Messages.get(this,"ability_no_charge道术"));
//			return;
//		}
//
//		if (QuickSlotButton.targetingSlot!=-1&&
//			Dungeon.quickslot.getItem(QuickSlotButton.targetingSlot) == 铜钱剑.this) {
//			targetingSpell = quickSpell;
//			int cell = QuickSlotButton.autoAim(QuickSlotButton.lastTarget, 铜钱剑.this);
//
//			if (cell != -1){
//				GameScene.handleCell(cell);
//			} else {
//				//couldn't auto-aim, just target the position and hope for the best.
//				GameScene.handleCell( QuickSlotButton.lastTarget.pos );
//			}
//		} else {
//			quickSpell.onCast(铜钱剑.this,Dungeon.hero);
//
//			if (quickSpell.targetingFlags() != -1 && Dungeon.quickslot.contains(铜钱剑.this)){
//				targetingSpell = quickSpell;
//				QuickSlotButton.useTargeting(Dungeon.quickslot.getSlot(铜钱剑.this));
//			}
//		}


//		if (Dungeon.hero.subClass!=HeroSubClass.CHAMPION){
//			return;
//		}
//
//		if (Dungeon.hero.belongings.secondWep == null && Dungeon.hero.belongings.backpack.items.size() >= Dungeon.hero.belongings.backpack.capacity()){
//			GLog.w(Messages.get(Weapon.class,"swap_full"));
//			return;
//		}
//
//		Weapon temp = Dungeon.hero.belongings.weapon;
//		Dungeon.hero.belongings.weapon = Dungeon.hero.belongings.secondWep;
//		Dungeon.hero.belongings.secondWep = temp;
//
//		Dungeon.hero.sprite.operate();
//		Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
//
//		ActionIndicator.setAction(this);
//		Item.updateQuickslot();
//		AttackIndicator.updateState();
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

				float delay = REGENERATION_DELAY;
				if (chaliceLevel != -1 && target.buff(MagicImmune.class) == null) {
					if (chaliceCursed) {
						delay *= 1.5f;
					} else {
						//15% boost at +0, scaling to a 500% boost at +10
						delay -= 1.33f + chaliceLevel*0.667f;
						delay /= 能量之戒.artifactChargeMultiplier(target);
					}
				}

				//salt cube is turned off while regen is disabled.
				if (target.buff(LockedFloor.class) == null) {
					delay /= SaltCube.healthRegenMultiplier();
				}

				partialRegen += 1f / delay;

				if (partialRegen >= 1) {
					int x =0;
					if(target instanceof Hero hero){
						if(hero.天赋(Talent.孤立无援)&&!hero.视野敌人()){
							x+=hero.天赋生命力(Talent.孤立无援,0.15f);
						}
						x+=hero.天赋生命力(Talent.钢铁之盾,0.16f);
						x+=hero.天赋生命力(Talent.绝望安息,0.14f);
						
						if(!(hero.heroClass(HeroClass.机器)||hero.heroClass(HeroClass.凌云)))
						hero.回血(Math.round(partialRegen+
										   hero.生命力(0.14f)+x
					));
					partialRegen -= (int)partialRegen;
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

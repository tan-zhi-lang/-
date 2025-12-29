

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mimic;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.effects.Surprise;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class MasterThievesArmband extends Artifact {

	{
		image = 物品表.ARTIFACT_ARMBAND;

		levelCap = 10;

		charge = 0;
		partialCharge = 0;
		chargeCap = 5+ 等级()/2;

		defaultAction = AC_STEAL;
	}

	public static final String AC_STEAL = "STEAL";

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		if (isEquipped(hero)
				&& charge > 0
				&& hero.buff(MagicImmune.class) == null
				&& !cursed) {
			actions.add(AC_STEAL);
		}
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);

		if (hero.buff(MagicImmune.class) != null) return;

		if (action.equals(AC_STEAL)){

			curUser = hero;

			if (!isEquipped( hero )) {
				GLog.i( Messages.get(Artifact.class, "need_to_equip") );
				usesTargeting = false;

			} else if (charge < 1) {
				GLog.i( Messages.get(this, "no_charge") );
				usesTargeting = false;

			} else if (cursed) {
				GLog.w( Messages.get(this, "cursed") );
				usesTargeting = false;

			} else {
				usesTargeting = true;
				GameScene.selectCell(targeter);
			}

		}
	}

	public CellSelector.Listener targeter = new CellSelector.Listener(){

		@Override
		public void onSelect(Integer target) {

			if (target == null) {
				return;
			} else if (Actor.findChar(target) == null){
			
			} else if (Dungeon.level.distance(curUser.pos, target)>curUser.攻击范围()){
				GLog.w( Messages.get(MasterThievesArmband.class, "no_target") );
			} else {
				Char ch = Actor.findChar(target);
				 if (ch instanceof Mob) {
					 //不是敌人不是宝箱，不是中立
				 if (ch instanceof Mimic&&ch.alignment==Char.Alignment.NEUTRAL){
					 return;
				 }
					curUser.busy();
					curUser.sprite.attack(target, new Callback() {
						@Override
						public void call() {
							Sample.INSTANCE.play(Assets.Sounds.HIT);

							boolean surprised = ((Mob) ch).surprisedBy(curUser, false);
							float lootMultiplier = 1f + 0.1f* 等级();
							int debuffDuration = 3 + 等级()/2;

							Invisibility.dispel(curUser);

							if (surprised){
								lootMultiplier += 0.5f;
								Surprise.hit(ch);
								Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
								debuffDuration += 2;
								exp += 2;
							}

							float lootChance = ((Mob) ch).lootChance() * lootMultiplier;

//							if (Dungeon.hero.等级 > ((Mob) ch).最大等级 + 2) {
//								lootChance = 0;
//							} else
							if (ch.buff(StolenTracker.class) != null){
								lootChance = 0;
							}

							if (lootChance == 0){
								GLog.w(Messages.get(MasterThievesArmband.class, "no_steal"));
							} else if (Random.Float() <= lootChance){
								Item loot = ((Mob) ch).createLoot();
								boolean 有偷的=true;
								if(ch instanceof Shopkeeper s){
									if(s.商人信标){
										s.商人信标=false;
									}else{
										有偷的=false;
										//									GLog.w( Messages.get(MasterThievesArmband.class, "steal_shopkeeper") );
									}
								}
								
								if (loot==null||!有偷的){//Evan没写空的情况
									GLog.i(Messages.get(MasterThievesArmband.class, "failed_steal"));
									Buff.施加(ch, StolenTracker.class).setItemStolen(false);
								} else {
									if (loot.doPickUp(curUser)) {
										//item collection happens instantly
										curUser.spend(-curUser.攻击延迟());
									} else {
										Dungeon.level.drop(loot, curUser.pos).sprite().drop();
									}
									GLog.i(Messages.get(MasterThievesArmband.class, "stole_item", loot.name()));
									Buff.施加(ch, StolenTracker.class).setItemStolen(true);
								}
							} else {
								GLog.i(Messages.get(MasterThievesArmband.class, "failed_steal"));
								Buff.施加(ch, StolenTracker.class).setItemStolen(false);
							}

							Buff.延长(ch, Blindness.class, debuffDuration);
							Buff.延长(ch, Cripple.class, debuffDuration);

							artifactProc(ch, visiblyUpgraded(), 1);

							charge--;
							exp += 3;
							Talent.onArtifactUsed(Dungeon.hero);
							while (exp >= (10 + Math.round(3.33f * 等级())) && 等级() < levelCap) {
								exp -= 10 + Math.round(3.33f * 等级());
								Catalog.countUse(MasterThievesArmband.class);
								GLog.p(Messages.get(MasterThievesArmband.class, "level_up"));
								升级();
							}
							Item.updateQuickslot();
							curUser.next();
						}
					});

				}
			}

		}

		@Override
		public String prompt() {
			return Messages.get(MasterThievesArmband.class, "prompt");
		}
	};

	//counter of 0 for attempt but no success, 1 for success
	public static class StolenTracker extends CounterBuff {
		{ revivePersists = true; }
		public void setItemStolen(boolean stolen){ if (stolen) countUp(1); }
		public boolean itemWasStolen(){ return count() > 0; }
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new Thievery();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (cursed || target.buff(MagicImmune.class) != null) return;
		if (charge < chargeCap) {
			partialCharge += 0.1f * amount;
			while (partialCharge >= 1f) {
				charge++;
				partialCharge--;
			}
			if (charge >= chargeCap) {
				GLog.p(Messages.get(MasterThievesArmband.class, "full"));
				partialCharge = 0;
				charge = chargeCap;
			}
			updateQuickslot();
		}
	}

	@Override
	public Item 升级() {
		chargeCap = 5 + (等级()+1)/2;
		return super.升级();
	}

	@Override
	public String desc() {
		String desc = super.desc();

		if ( isEquipped (Dungeon.hero) ){
			if (cursed){
				desc += "\n\n" + Messages.get(this, "desc_cursed");
			} else {
				desc += "\n\n" + Messages.get(this, "desc_worn");
			}
		}

		return desc;
	}

	public class Thievery extends ArtifactBuff {

		@Override
		public boolean act() {
			if (cursed && Dungeon.gold > 0 && Random.Int(5) == 0){
				Dungeon.gold(-1);
				updateQuickslot();
			}

			spend(TICK);
			return true;
		}

		public void gainCharge(float levelPortion) {
			if (cursed || target.buff(MagicImmune.class) != null) return;

			if (charge < chargeCap){
				float chargeGain = 3f * levelPortion;
				chargeGain *= 能量之戒.artifactChargeMultiplier(target);

				partialCharge += chargeGain;
				while (partialCharge > 1f){
					partialCharge--;
					charge++;
					updateQuickslot();

					if (charge == chargeCap){
						GLog.p( Messages.get(MasterThievesArmband.class, "full") );
						partialCharge = 0;
					}
				}

			} else {
				partialCharge = 0f;
			}
		}
		
		public boolean steal(Item item){
			int chargesUsed = chargesToUse(item);
			float stealChance = stealChance(item);
			if (Random.Float() > stealChance){
				return false;
			} else {
				charge -= chargesUsed;
				exp += 4 * chargesUsed;
				GLog.i(Messages.get(MasterThievesArmband.class, "stole_item", item.name()));

				Talent.onArtifactUsed(Dungeon.hero);
				while (exp >= (10 + Math.round(3.33f * 等级())) && 等级() < levelCap) {
					exp -= 10 + Math.round(3.33f * 等级());
					Catalog.countUse(MasterThievesArmband.class);
					GLog.p(Messages.get(MasterThievesArmband.class, "level_up"));
					升级();
				}
				updateQuickslot();
				return true;
			}
		}

		public float stealChance(Item item){
			int chargesUsed = chargesToUse(item);
			float val = chargesUsed * (10 + 等级()/2f);
			return Math.min(1f, val/item.金币());
		}

		public int chargesToUse(Item item){
			int value = item.金币();
			float valUsing = 0;
			int chargesUsed = 0;
			while (valUsing < value && chargesUsed < charge){
				valUsing += 10 + 等级()/2f;
				chargesUsed++;
			}
			return chargesUsed;
		}
	}


}

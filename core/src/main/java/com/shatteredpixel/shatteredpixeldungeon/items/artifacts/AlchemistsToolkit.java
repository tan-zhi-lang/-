

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.AlchemyScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class AlchemistsToolkit extends Artifact {

	{
		image = 物品表.ARTIFACT_TOOLKIT;
		defaultAction = AC_BREW;

		levelCap = 10;
		
		charge = 0;
		partialCharge = 0;
	}

	public static final String AC_BREW = "BREW";
	public static final String AC_ENERGIZE = "ENERGIZE";

	private float warmUpDelay;

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (isEquipped( hero ) && !cursed && hero.buff(MagicImmune.class) == null) {
			actions.add(AC_BREW);
			if (等级() < levelCap) {
				actions.add(AC_ENERGIZE);
			}
		}
		return actions;
	}

	@Override
	public void execute(Hero hero, String action ) {

		super.execute(hero, action);

		if (hero.buff(MagicImmune.class) != null) return;

		if (action.equals(AC_BREW)){
			if (!isEquipped(hero))              GLog.i( Messages.get(this, "need_to_equip") );
			else if (cursed)                    GLog.w( Messages.get(this, "cursed") );
			else if (warmUpDelay > 0)           GLog.w( Messages.get(this, "not_ready") );
			else {
				AlchemyScene.assignToolkit(this);
				Game.switchScene(AlchemyScene.class);
			}
			
		} else if (action.equals(AC_ENERGIZE)){
			if (!isEquipped(hero))              GLog.i( Messages.get(this, "need_to_equip") );
			else if (cursed)                    GLog.w( Messages.get(this, "cursed") );
			else if (Dungeon.energy < 6)        GLog.w( Messages.get(this, "need_energy") );
			else {

				final int maxLevels = Math.min(levelCap - 等级(), Dungeon.energy/6);

				String[] options;
				if (maxLevels > 1){
					options = new String[]{ Messages.get(this, "energize_1"), Messages.get(this, "energize_all", 6*maxLevels, maxLevels)};
				} else {
					options = new String[]{ Messages.get(this, "energize_1")};
				}

				GameScene.show(new WndOptions(new ItemSprite(image),
						Messages.titleCase(name()),
						Messages.get(this, "energize_desc"),
						options){
					@Override
					protected void onSelect(int index) {
						super.onSelect(index);

						if (index == 0){
							Dungeon.energy -= 6;
							Sample.INSTANCE.play(Assets.Sounds.DRINK);
							Sample.INSTANCE.playDelayed(Assets.Sounds.PUFF, 0.5f);
							Dungeon.hero.sprite.operate();
							升级();
							Catalog.countUse(AlchemistsToolkit.class);
						} else if (index == 1){
							Dungeon.energy -= 6*maxLevels;
							Sample.INSTANCE.play(Assets.Sounds.DRINK);
							Sample.INSTANCE.playDelayed(Assets.Sounds.PUFF, 0.5f);
							Dungeon.hero.sprite.operate();
							升级(maxLevels);
							Catalog.countUses(AlchemistsToolkit.class, maxLevels);
						}

					}

					@Override
					protected boolean hasIcon(int index) {
						return true;
					}

					@Override
					protected Image getIcon(int index) {
						return new ItemSprite(物品表.ENERGY);
					}
				});
			}
		}

		updateQuickslot();
	}

	@Override
	public String status() {
		if (isEquipped(Dungeon.hero) && warmUpDelay > 0 && !cursed){
			return Messages.format( "%d%%", Math.max(0, 100 - (int)warmUpDelay) );
		} else {
			return super.status();
		}
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new kitEnergy();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (target.buff(MagicImmune.class) != null) return;
		partialCharge += 0.25f*amount;
		while (partialCharge >= 1){
			partialCharge--;
			charge++;
			updateQuickslot();
		}
	}

	public int availableEnergy(){
		return charge;
	}

	public int consumeEnergy(int amount){
		int result = amount - charge;
		charge = Math.max(0, charge - amount);
		Talent.onArtifactUsed(Dungeon.hero);
		return Math.max(0, result);
	}

	@Override
	public String desc() {
		String result = Messages.get(this, "desc");

		if (isEquipped(Dungeon.hero)) {
			if (cursed)                 result += "\n\n" + Messages.get(this, "desc_cursed");
			else if (warmUpDelay > 0)   result += "\n\n" + Messages.get(this, "desc_warming");
			else                        result += "\n\n" + Messages.get(this, "desc_hint");
		}
		
		return result;
	}
	
	@Override
	public boolean doEquip(Hero hero) {
		if (super.doEquip(hero)){
			warmUpDelay = 101f;
			return true;
		} else {
			return false;
		}
	}
	
	private static final String WARM_UP = "warm_up";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(WARM_UP, warmUpDelay);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		warmUpDelay = bundle.getFloat(WARM_UP);
	}
	
	public class kitEnergy extends ArtifactBuff {

		@Override
		public boolean act() {

			if (warmUpDelay > 0){
				if (等级() == 10){
					warmUpDelay = 0;
				} else if (warmUpDelay == 101){
					warmUpDelay = 100f;
				} else if (!cursed && target.buff(MagicImmune.class) == null) {
					float turnsToWarmUp = (int) Math.pow(10 - 等级(), 2);
					warmUpDelay -= 100 / turnsToWarmUp;
				}
				updateQuickslot();
			}

			spend(TICK);
			return true;
		}

		public void gainCharge(float levelPortion) {
			if (cursed || target.buff(MagicImmune.class) != null) return;

			//generates 2 energy every hero level, +1 energy per toolkit level
			//to a max of 12 energy per hero level
			//This means that energy absorbed into the kit is recovered in 5 hero levels
			float chargeGain = (2 + 等级()) * levelPortion;
			chargeGain *= 能量之戒.artifactChargeMultiplier(target);
			partialCharge += chargeGain;

			//charge is in increments of 1 energy.
			while (partialCharge >= 1) {
				charge++;
				partialCharge -= 1;

				updateQuickslot();
			}
		}

	}

}

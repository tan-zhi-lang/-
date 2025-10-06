

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.ClericSpell;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.GuidingLight;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndClericSpells;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 神圣法典 extends Artifact {

	{
		image = 物品表.神圣法典;

		exp = 0;
		levelCap = 10;

		charge = Math.min(等级()+3, 10);
		partialCharge = 0;
		chargeCap = Math.min(等级()+3, 10);

		defaultAction = AC_CAST;

		黄色 = true;
		unique = true;
		bones = false;
	}

	public static final String AC_CAST = "CAST";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if ((isEquipped( hero ) || hero.天赋(Talent.LIGHT_READING))
				&& !cursed
				&& hero.buff(MagicImmune.class) == null) {
			actions.add(AC_CAST);
		}
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute(hero, action);

		if (hero.buff(MagicImmune.class) != null) return;

		if (action.equals(AC_CAST)) {

			if (!isEquipped(hero) && !hero.天赋(Talent.LIGHT_READING)) GLog.i(Messages.get(Artifact.class,"need_to_equip"));
			else if (cursed)       GLog.i( Messages.get(this, "cursed") );
			else {

				GameScene.show(new WndClericSpells(this, hero, false));

			}

		}
	}

	//used to ensure tome has variable targeting logic for whatever spell is being case
	public ClericSpell targetingSpell = null;

	@Override
	public int targetingPos(Hero user, int dst) {
		if (targetingSpell == null || targetingSpell.targetingFlags() == -1) {
			return super.targetingPos(user, dst);
		} else {
			return new Ballistica( user.pos, dst, targetingSpell.targetingFlags() ).collisionPos;
		}
	}

	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		if (super.doUnequip(hero, collect, single)){
			if (collect && hero.天赋(Talent.LIGHT_READING)){
				activate(hero);
			}

			return true;
		} else
			return false;
	}

	@Override
	public boolean 放背包(Bag container ) {
		if (super.放背包(container)){
			if (container.owner instanceof Hero
					&& passiveBuff == null
					&& ((Hero) container.owner).天赋(Talent.LIGHT_READING)){
				activate((Hero) container.owner);
			}
			return true;
		} else{
			return false;
		}
	}

	@Override
	protected void onDetach() {
		if (passiveBuff != null){
			passiveBuff.detach();
			passiveBuff = null;
		}
	}

	public boolean canCast( Hero hero, ClericSpell spell ){
		return (isEquipped(hero) || (Dungeon.hero.天赋(Talent.LIGHT_READING)&&hero.belongings.contains(this)))
				&& hero.buff(MagicImmune.class) == null
				&& charge >= spell.chargeUse(hero)
				&& spell.canCast(hero);
	}

	public void spendCharge( float chargesSpent ){
		partialCharge -= chargesSpent;
		while (partialCharge < 0){
			charge--;
			partialCharge++;
		}

		//target hero level is 1 + 2*tome level
		int lvlDiffFromTarget = Dungeon.hero.等级 - (1+ 等级()*2);
		//plus an extra one for each level after 6
		if (等级() >= 7){
			lvlDiffFromTarget -= 等级()-6;
		}

		if (lvlDiffFromTarget >= 0){
			exp += Math.round(chargesSpent * 10f * Math.pow(1.1f, lvlDiffFromTarget));
		} else {
			exp += Math.round(chargesSpent * 10f * Math.pow(0.75f, -lvlDiffFromTarget));
		}

		if (exp >= (等级() + 1) * 50 && 等级() < levelCap) {
			升级();
			Catalog.countUse(神圣法典.class);
			exp -= 等级() * 50;
			GLog.p(Messages.get(this, "levelup"));

		}

		updateQuickslot();
	}

	public void directCharge(float amount){
		if (charge < chargeCap) {
			partialCharge += amount;
			while (partialCharge >= 1f) {
				charge++;
				partialCharge--;
			}
			if (charge >= chargeCap){
				partialCharge = 0;
				charge = chargeCap;
			}
			updateQuickslot();
		}
		updateQuickslot();
	}

	@Override
	public Item 升级() {
		chargeCap = Math.min(chargeCap + 1, 10);
		return super.升级();
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new TomeRecharge();
	}

	@Override
	public void charge(Hero target, float amount) {
		if (cursed || target.buff(MagicImmune.class) != null) return;

		if (charge < chargeCap) {
			if (!isEquipped(target)) amount *= target.天赋点数(Talent.LIGHT_READING,0.25f);
			partialCharge += 0.25f*amount;
			while (partialCharge >= 1f) {
				charge++;
				partialCharge--;
			}
			if (charge >= chargeCap){
				partialCharge = 0;
				charge = chargeCap;
			}
			updateQuickslot();
		}
	}

	private ClericSpell quickSpell = null;

	public void setQuickSpell(ClericSpell spell){
		if (quickSpell == spell){
			quickSpell = null; //re-assigning the same spell clears the quick spell
			if (passiveBuff != null){
				ActionIndicator.clearAction((ActionIndicator.Action) passiveBuff);
			}
		} else {
			quickSpell = spell;
			if (passiveBuff != null){
				ActionIndicator.setAction((ActionIndicator.Action) passiveBuff);
			}
		}
	}

	private static final String QUICK_CLS = "quick_cls";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		if (quickSpell != null) {
			bundle.put(QUICK_CLS, quickSpell.getClass());
		}
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(QUICK_CLS)){
			Class quickCls = bundle.getClass(QUICK_CLS);
			for (ClericSpell spell : ClericSpell.getAllSpells()){
				if (spell.getClass() == quickCls){
					quickSpell = spell;
				}
			}
		}
	}

	public class TomeRecharge extends ArtifactBuff implements ActionIndicator.Action {

		@Override
		public boolean attachTo(Char target) {
			if (super.attachTo(target)) {
				if (quickSpell != null) ActionIndicator.setAction(this);
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void detach() {
			super.detach();
			ActionIndicator.clearAction(this);
		}

		@Override
		public boolean act() {
			if (charge < chargeCap && !cursed && target.buff(MagicImmune.class) == null) {
				if (再生.regenOn()) {
					float missing = (chargeCap - charge);
					if (等级() > 7) missing += 5*(等级() - 7)/3f;
					float turnsToCharge = (45 - missing);
					turnsToCharge /= 能量之戒.artifactChargeMultiplier(target);
					float chargeToGain = (1f / turnsToCharge);
					if (!isEquipped(Dungeon.hero)){
						chargeToGain *= Dungeon.hero.天赋点数(Talent.LIGHT_READING,025f);
					}
					partialCharge += chargeToGain;
				}

				while (partialCharge >= 1) {
					charge++;
					partialCharge -= 1;
					if (charge == chargeCap){
						partialCharge = 0;
					}

				}
			} else {
				partialCharge = 0;
			}

			updateQuickslot();

			spend( TICK );

			return true;
		}

		@Override
		public String actionName() {
			return quickSpell.name();
		}

		@Override
		public int actionIcon() {
			return quickSpell.icon() + 32;
		}
		@Override
		public Visual primaryVisual() {
			Image ico= new HeroIcon(this);;
			ico.width += 4; //shift slightly to the left to separate from smaller icon
			return ico;
		}
		@Override
		public Visual secondaryVisual() {
			Image ico= new ItemSprite(new 神圣法典());
			ico.scale.set(PixelScene.align(0.51f));
			ico.brightness(0.6f);
			return ico;
		}
		@Override
		public int indicatorColor() {
			if (quickSpell == GuidingLight.INSTANCE && quickSpell.chargeUse(Dungeon.hero) == 0){
				return 0x0063ff;
			} else {
				return 0x002157;
			}
		}

		@Override
		public void doAction() {
			if (cursed){
				GLog.w(Messages.get(神圣法典.this, "cursed"));
				return;
			}

			if (!canCast(Dungeon.hero, quickSpell)){
				GLog.w(Messages.get(神圣法典.this, "no_spell"));
				return;
			}

			if (QuickSlotButton.targetingSlot != -1 &&
					Dungeon.quickslot.getItem(QuickSlotButton.targetingSlot) == 神圣法典.this) {
				targetingSpell = quickSpell;
				int cell = QuickSlotButton.autoAim(QuickSlotButton.lastTarget, 神圣法典.this);

				if (cell != -1){
					GameScene.handleCell(cell);
				} else {
					//couldn't auto-aim, just target the position and hope for the best.
					GameScene.handleCell( QuickSlotButton.lastTarget.pos );
				}
			} else {
				quickSpell.onCast(神圣法典.this, Dungeon.hero);

				if (quickSpell.targetingFlags() != -1 && Dungeon.quickslot.contains(神圣法典.this)){
					targetingSpell = quickSpell;
					QuickSlotButton.useTargeting(Dungeon.quickslot.getSlot(神圣法典.this));
				}
			}
		}
	}

}

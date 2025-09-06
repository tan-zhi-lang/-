

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.AscendedForm;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

import java.util.ArrayList;

public abstract class ClericSpell {

	public abstract void onCast(神圣法典 tome, Hero hero);

	public float chargeUse( Hero hero ){
		return 1;
	}

	public boolean canCast( Hero hero ){
		return true;
	}

	public String name(){
		return Messages.get(this, "name");
	}

	public String shortDesc(){
		return Messages.get(this, "short_desc") + " " + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	public String desc(){
		return Messages.get(this, "desc") + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	public boolean usesTargeting(){
		return false;
	}

	public int targetingFlags(){
		return -1; //-1 for no targeting
	}

	public int icon(){
		return HeroIcon.NONE;
	}

	public void onSpellCast(神圣法典 tome, Hero hero){
		Invisibility.dispel();
		//施法获得护盾
//		if (hero.buff(Talent.SatiatedSpellsTracker.class) != null){
//			int amount = hero.天赋点数(Talent.,5);
//			Buff.施加(hero, Barrier.class).设置(amount);
//			Char ally = PowerOfMany.getPoweredAlly();
//			if (ally != null && ally.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null){
//				Buff.施加(ally, Barrier.class).设置(amount);
//			}
//			hero.buff(Talent.SatiatedSpellsTracker.class).detach();
//		}
		tome.spendCharge(chargeUse(hero));
		Talent.onArtifactUsed(hero);
		if (hero.subClass == HeroSubClass.PALADIN){
			if (this != HolyWeapon.INSTANCE && hero.buff(HolyWeapon.HolyWepBuff.class) != null){
				hero.buff(HolyWeapon.HolyWepBuff.class).extend(10*chargeUse(hero));
			}
			if (this != HolyWard.INSTANCE && hero.buff(HolyWard.HolyArmBuff.class) != null){
				hero.buff(HolyWard.HolyArmBuff.class).extend(10*chargeUse(hero));
			}
		}

		if (hero.buff(AscendedForm.AscendBuff.class) != null){
			hero.buff(AscendedForm.AscendBuff.class).spellCasts++;
			hero.buff(AscendedForm.AscendBuff.class).增加((int)(10*chargeUse(hero)));
		}
	}

	public static ArrayList<ClericSpell> getSpellList(Hero cleric, int tier){
		ArrayList<ClericSpell> spells = new ArrayList<>();

		if (tier == 1) {

			spells.add(GuidingLight.INSTANCE);
			spells.add(HolyWeapon.INSTANCE);
			spells.add(HolyWard.INSTANCE);

			if (cleric.天赋(Talent.HOLY_INTUITION)) {
				spells.add(HolyIntuition.INSTANCE);
			}

			if (cleric.天赋(Talent.SHIELD_OF_LIGHT)) {
				spells.add(ShieldOfLight.INSTANCE);
			}

		} else if (tier == 2) {

			if (cleric.天赋(Talent.RECALL_INSCRIPTION)){
				spells.add(RecallInscription.INSTANCE);
			}

			if (cleric.天赋(Talent.SUNRAY)){
				spells.add(Sunray.INSTANCE);
			}

			if (cleric.天赋(Talent.DIVINE_SENSE)) {
				spells.add(DivineSense.INSTANCE);
			}

			if (cleric.天赋(Talent.BLESS)){
				spells.add(BlessSpell.INSTANCE);
			}

		} else if (tier == 3){

			if (cleric.subClass == HeroSubClass.PRIEST) {
				spells.add(Radiance.INSTANCE);

			} else if (cleric.subClass == HeroSubClass.PALADIN){
				spells.add(Smite.INSTANCE);
			}

			if (cleric.天赋(Talent.CLEANSE)){
				spells.add(Cleanse.INSTANCE);
			}

			if (cleric.天赋(Talent.HOLY_LANCE)){
				spells.add(HolyLance.INSTANCE);
			}
			if (cleric.天赋(Talent.HALLOWED_GROUND)){
				spells.add(HallowedGround.INSTANCE);
			}
			if (cleric.天赋(Talent.MNEMONIC_PRAYER)){
				spells.add(MnemonicPrayer.INSTANCE);
			}

			if (cleric.天赋(Talent.LAY_ON_HANDS)){
				spells.add(LayOnHands.INSTANCE);
			}
			if (cleric.天赋(Talent.AURA_OF_PROTECTION)){
				spells.add(AuraOfProtection.INSTANCE);
			}
			if (cleric.天赋(Talent.WALL_OF_LIGHT)){
				spells.add(WallOfLight.INSTANCE);
			}

		} else if (tier == 4){

			if (cleric.天赋(Talent.DIVINE_INTERVENTION)){
				spells.add(DivineIntervention.INSTANCE);
			}
			if (cleric.天赋(Talent.JUDGEMENT)){
				spells.add(Judgement.INSTANCE);
			}
			if (cleric.天赋(Talent.FLASH)){
				spells.add(Flash.INSTANCE);
			}

			if (cleric.天赋(Talent.BODY_FORM)){
				spells.add(BodyForm.INSTANCE);
			}
			if (cleric.天赋(Talent.MIND_FORM)){
				spells.add(MindForm.INSTANCE);
			}
			if (cleric.天赋(Talent.SPIRIT_FORM)){
				spells.add(SpiritForm.INSTANCE);
			}

			if (cleric.天赋(Talent.BEAMING_RAY)){
				spells.add(BeamingRay.INSTANCE);
			}
			if (cleric.天赋(Talent.LIFE_LINK)){
				spells.add(LifeLinkSpell.INSTANCE);
			}
			if (cleric.天赋(Talent.STASIS)){
				spells.add(Stasis.INSTANCE);
			}

		}

		return spells;
	}

	public static ArrayList<ClericSpell> getAllSpells() {
		ArrayList<ClericSpell> spells = new ArrayList<>();
		spells.add(GuidingLight.INSTANCE);
		spells.add(HolyWeapon.INSTANCE);
		spells.add(HolyWard.INSTANCE);
		spells.add(HolyIntuition.INSTANCE);
		spells.add(ShieldOfLight.INSTANCE);
		spells.add(RecallInscription.INSTANCE);
		spells.add(Sunray.INSTANCE);
		spells.add(DivineSense.INSTANCE);
		spells.add(BlessSpell.INSTANCE);
		spells.add(Cleanse.INSTANCE);
		spells.add(Radiance.INSTANCE);
		spells.add(Smite.INSTANCE);
		spells.add(LayOnHands.INSTANCE);
		spells.add(AuraOfProtection.INSTANCE);
		spells.add(WallOfLight.INSTANCE);
		spells.add(HolyLance.INSTANCE);
		spells.add(HallowedGround.INSTANCE);
		spells.add(MnemonicPrayer.INSTANCE);
		spells.add(DivineIntervention.INSTANCE);
		spells.add(Judgement.INSTANCE);
		spells.add(Flash.INSTANCE);
		spells.add(BodyForm.INSTANCE);
		spells.add(MindForm.INSTANCE);
		spells.add(SpiritForm.INSTANCE);
		spells.add(BeamingRay.INSTANCE);
		spells.add(LifeLinkSpell.INSTANCE);
		spells.add(Stasis.INSTANCE);
		return spells;
	}
}

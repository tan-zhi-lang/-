

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍之额;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

import java.util.ArrayList;

public abstract class 忍术{

	public abstract void onCast(叛忍之额 tome,Hero hero);

	public int chargeUse( Hero hero ){
		return 1;
	}

	public boolean canCast( Hero hero ){
		return true;
	}

	public String name(){
		return Messages.get(this, "name");
	}

	public String shortDesc(){
		return Messages.get(this, "short_desc") + " " + Messages.get(this, "charge_cost", chargeUse(Dungeon.hero));
	}

	public String desc(){
		return Messages.get(this, "desc") + "\n\n" + Messages.get(this, "charge_cost", chargeUse(Dungeon.hero));
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

	public void onSpellCast(叛忍之额 tome, Hero hero){
		Invisibility.dispel();
		tome.spendCharge(chargeUse(hero));
		Talent.onArtifactUsed(hero);
	}
	public static ArrayList<忍术> getSpellList(Hero cleric,int tier){
		ArrayList<忍术> spells = new ArrayList<>();

		if (tier == 1) {
//			if (cleric.天赋(Talent.净除道术)) {
				spells.add(风切忍术.INSTANCE);
//			}
//			if (cleric.天赋(Talent.痛命之术)) {
//				spells.add(痛命之术.INSTANCE);
//			}
//			if (cleric.天赋(Talent.死血之术)) {
//				spells.add(死血之术.INSTANCE);
//			}
		} else if (tier == 2) {
//			if (cleric.天赋(Talent.血历之术)) {
//				spells.add(血历之术.INSTANCE);
//			}
//			if (cleric.天赋(Talent.血爆之术)) {
//				spells.add(血爆之术.INSTANCE);
//			}
//			if (cleric.天赋(Talent.饮血之术)) {
//				spells.add(饮血之术.INSTANCE);
//			}
//			if (cleric.天赋(Talent.换血之术)) {
//				spells.add(换血之术.INSTANCE);
//			}
		} else if (tier == 3){
//			if (cleric.天赋(Talent.物到之术)) {
//				spells.add(物到之术.INSTANCE);
//			}
		} else if (tier == 4){


		}

		return spells;
	}

	public static ArrayList<忍术> getAllSpells() {
		ArrayList<忍术> spells = new ArrayList<>();
//		spells.add(净除道术.INSTANCE);
//		spells.add(死血之术.INSTANCE);
//		spells.add(痛命之术.INSTANCE);
//
//		spells.add(血历之术.INSTANCE);
//		spells.add(血爆之术.INSTANCE);
//		spells.add(饮血之术.INSTANCE);
//		spells.add(换血之术.INSTANCE);
//
//		spells.add(物到之术.INSTANCE);
		return spells;
	}
}

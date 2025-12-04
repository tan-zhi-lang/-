

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

import java.util.ArrayList;

public abstract class 道术{

	public abstract void onCast(本命玉佩 tome,Hero hero);

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

	public void onSpellCast(本命玉佩 tome,Hero hero){
		Invisibility.dispel();
		tome.spendCharge(chargeUse(hero));
		Talent.onArtifactUsed(hero);
	}

	public static ArrayList<道术> getSpellList(Hero cleric,int tier){
		ArrayList<道术> spells = new ArrayList<>();

		if (tier == 1) {
				spells.add(符咒.INSTANCE);
		} else if (tier == 2) {
		} else if (tier == 3){
		} else if (tier == 4){


		}

		return spells;
	}

	public static ArrayList<道术> getAllSpells() {
		ArrayList<道术> spells = new ArrayList<>();
		spells.add(符咒.INSTANCE);
		return spells;
	}
}

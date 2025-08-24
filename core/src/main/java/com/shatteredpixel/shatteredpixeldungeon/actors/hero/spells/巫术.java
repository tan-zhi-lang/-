

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.AscendedForm;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

import java.util.ArrayList;

public abstract class 巫术 {

	public abstract void onCast(灵月法杖 tome, Hero hero);

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

	public void onSpellCast(灵月法杖 tome, Hero hero){
		Invisibility.notimedispel();
		tome.wandUsed();
	}

	public static ArrayList<巫术> getSpellList(Hero cleric, int tier){
		ArrayList<巫术> spells = new ArrayList<>();

		if (tier == 1) {
			if (cleric.有天赋(Talent.祭鉴之术)) {
				spells.add(祭鉴之术.INSTANCE);
			}
			if (cleric.有天赋(Talent.痛命之术)) {
				spells.add(痛命之术.INSTANCE);
			}
			if (cleric.有天赋(Talent.死血之术)) {
				spells.add(死血之术.INSTANCE);
			}
		} else if (tier == 2) {
			if (cleric.有天赋(Talent.血历之术)) {
				spells.add(血历之术.INSTANCE);
			}
			if (cleric.有天赋(Talent.血爆之术)) {
				spells.add(血爆之术.INSTANCE);
			}
			if (cleric.有天赋(Talent.饮血之术)) {
				spells.add(饮血之术.INSTANCE);
			}
			if (cleric.有天赋(Talent.换血之术)) {
				spells.add(换血之术.INSTANCE);
			}
		} else if (tier == 3){
			if (cleric.有天赋(Talent.物到之术)) {
				spells.add(物到之术.INSTANCE);
			}
		} else if (tier == 4){


		}

		return spells;
	}

	public static ArrayList<巫术> getAllSpells() {
		ArrayList<巫术> spells = new ArrayList<>();
		spells.add(祭鉴之术.INSTANCE);
		spells.add(死血之术.INSTANCE);
		spells.add(痛命之术.INSTANCE);

		spells.add(血历之术.INSTANCE);
		spells.add(血爆之术.INSTANCE);
		spells.add(饮血之术.INSTANCE);
		spells.add(换血之术.INSTANCE);

		spells.add(物到之术.INSTANCE);
		return spells;
	}
}

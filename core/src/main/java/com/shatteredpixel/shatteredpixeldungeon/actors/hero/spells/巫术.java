

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

import java.util.ArrayList;

public abstract class 巫术 {

	public abstract void onCast(灵月法杖 tome, Hero hero);

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

	public void onSpellCast(灵月法杖 tome, Hero hero){
		Invisibility.notimedispel();
		tome.wandUsed();
	}

	public static ArrayList<巫术> getSpellList(Hero cleric, int tier){
		ArrayList<巫术> spells = new ArrayList<>();

		if (tier == 1) {
			spells.add(痛命.INSTANCE);
		} else if (tier == 2) {
			if(cleric.等级>6&&Badges.local.contains(Badges.Badge.BOSS_SLAIN_1))
			spells.add(死血.INSTANCE);
		} else if (tier == 3){
		} else if (tier == 4){


		}

		return spells;
	}

	public static ArrayList<巫术> getAllSpells() {
		ArrayList<巫术> spells = new ArrayList<>();
		spells.add(痛命.INSTANCE);
		spells.add(死血.INSTANCE);
		return spells;
	}
}

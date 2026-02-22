

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.四叶草法典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

import java.util.ArrayList;

public abstract class 法术{

	public abstract void onCast(四叶草法典 tome,Hero hero);

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

	public void onSpellCast(四叶草法典 tome,Hero hero){
		Invisibility.notimedispel();
		tome.spendCharge(chargeUse(hero));
		Talent.onArtifactUsed(hero);
	}
	public static ArrayList<法术> getSpellList(Hero cleric,int tier){
		ArrayList<法术> spells = new ArrayList<>();

		if (tier == 1) {
			spells.add(火球术.INSTANCE);
		} else if (tier == 2) {
			if(cleric.等级>6&&Badges.local.contains(Badges.Badge.BOSS_SLAIN_1))
				spells.add(火墙术.INSTANCE);
		} else if (tier == 3){
			if(cleric.天赋(Talent.破冰飞刃))
			spells.add(破冰飞刃.INSTANCE);

			if(cleric.天赋(Talent.冰魄之弓))
			spells.add(冰魄之弓.INSTANCE);
		} else if (tier == 4){


		}

		return spells;
	}

	public static ArrayList<法术> getAllSpells() {
		ArrayList<法术> spells = new ArrayList<>();
		spells.add(火球术.INSTANCE);
		spells.add(火墙术.INSTANCE);

		spells.add(破冰飞刃.INSTANCE);
		spells.add(冰魄之弓.INSTANCE);
		return spells;
	}
}

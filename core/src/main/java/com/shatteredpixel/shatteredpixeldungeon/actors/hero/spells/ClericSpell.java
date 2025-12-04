

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
		}

		if (hero.buff(AscendedForm.AscendBuff.class) != null){
			hero.buff(AscendedForm.AscendBuff.class).spellCasts++;
			hero.buff(AscendedForm.AscendBuff.class).增加((int)(10*chargeUse(hero)));
		}
	}

	public static ArrayList<ClericSpell> getSpellList(Hero cleric, int tier){
		ArrayList<ClericSpell> spells = new ArrayList<>();

		if (tier == 1) {

			spells.add(圣光.INSTANCE);

		} else if (tier == 2) {
		} else if (tier == 3){
		} else if (tier == 4){

		}

		return spells;
	}

	public static ArrayList<ClericSpell> getAllSpells() {
		ArrayList<ClericSpell> spells = new ArrayList<>();
		spells.add(圣光.INSTANCE);
		return spells;
	}
}

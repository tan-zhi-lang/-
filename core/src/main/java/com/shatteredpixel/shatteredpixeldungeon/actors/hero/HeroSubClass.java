

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.Game;

public enum HeroSubClass {

	NONE(HeroIcon.NONE),

	BERSERKER(HeroIcon.BERSERKER),
	GLADIATOR(HeroIcon.GLADIATOR),

	BATTLEMAGE(HeroIcon.BATTLEMAGE),
	WARLOCK(HeroIcon.WARLOCK),
	
	ASSASSIN(HeroIcon.ASSASSIN),
	FREERUNNER(HeroIcon.FREERUNNER),
	
	SNIPER(HeroIcon.SNIPER),
	WARDEN(HeroIcon.WARDEN),

	CHAMPION(HeroIcon.CHAMPION),
	MONK(HeroIcon.MONK),

	PRIEST(HeroIcon.PRIEST),
	PALADIN(HeroIcon.PALADIN),

	神秘学者(HeroIcon.神秘学者),
	黑魔导师(HeroIcon.黑魔导师);

	int icon;

	HeroSubClass(int icon){
		this.icon = icon;
	}
	
	public String title() {
		return Messages.get(this, name());
	}

	public String shortDesc() {
		return title();
	}

	public String desc() {
		//Include the staff effect description in the battlemage's desc if possible
		if (this == BATTLEMAGE){
			String desc = Messages.get(this, name() + "_desc");
			if (Game.scene() instanceof GameScene){
				法师魔杖 staff = Dungeon.hero.belongings.getItem(法师魔杖.class);
				if (staff != null && staff.wandClass() != null){
					desc += "\n\n" + Messages.get(staff.wandClass(), "bmage_desc");
					desc = desc.replaceAll("_", "");
				}
			}
			return desc;
		} else {
			return Messages.get(this, name() + "_desc");
		}
	}

	public int icon(){
		return icon;
	}

}

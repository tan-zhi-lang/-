

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.Game;

public enum HeroSubClass {

	NONE(HeroIcon.NONE),
	潜能觉醒(HeroIcon.NONE),

	狂战士(HeroIcon.BERSERKER),
	角斗士(HeroIcon.GLADIATOR),

	战斗法师(HeroIcon.BATTLEMAGE),
	术士(HeroIcon.WARLOCK),
	
	刺客(HeroIcon.ASSASSIN),
	疾行者(HeroIcon.FREERUNNER),
	
	狙击手(HeroIcon.SNIPER),
	守望者(HeroIcon.WARDEN),

	勇士(HeroIcon.CHAMPION),
	武者(HeroIcon.MONK),

	PRIEST(HeroIcon.PRIEST),
	PALADIN(HeroIcon.PALADIN),

	神秘学者(HeroIcon.神秘学者),
	黑魔导师(HeroIcon.黑魔导师),
	
	健身猛男(HeroIcon.NONE),
	盾之勇者(HeroIcon.盾之勇者),
	
	鬼师(HeroIcon.NONE),
	
	养殖专家(HeroIcon.NONE),
	
	元素忍者(HeroIcon.NONE);

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
		if (this==战斗法师){
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

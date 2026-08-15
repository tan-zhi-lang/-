

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

public enum HeroSubClass {

	NONE(HeroIcon.NONE),
	奇经八脉(HeroIcon.肉身成圣),

	不灭战士(HeroIcon.不灭战士),
	战斗法师(HeroIcon.BATTLEMAGE),

	冰魄剑神(HeroIcon.冰魄剑神),
	大魔法师(HeroIcon.大魔法师),

	冥法刺客(HeroIcon.ASSASSIN),
	神偷无影(HeroIcon.神偷无影),
	
	狙击手(HeroIcon.SNIPER),
	守望者(HeroIcon.WARDEN),

	武器大师(HeroIcon.CHAMPION),
	角斗士(HeroIcon.GLADIATOR),

	圣骑士(HeroIcon.PALADIN),
	祭司(HeroIcon.PRIEST),

	金刚独狼(HeroIcon.金刚独狼),
	黑魔导师(HeroIcon.黑魔导师),

	盾之勇者(HeroIcon.盾之勇者),
	轻装步兵(HeroIcon.轻装步兵),

	灵魂武者(HeroIcon.灵魂武者),
	内力武者(HeroIcon.MONK),

	死灵术士(HeroIcon.WARLOCK),
	真人(HeroIcon.真人),

	神兽之灵(HeroIcon.神兽之灵),
	养殖专家(HeroIcon.养殖专家),

	机械教主(HeroIcon.机械教主),
	魔法灵枢(HeroIcon.魔法灵枢),

	苦行者(HeroIcon.FREERUNNER),
	符文法师(HeroIcon.符文法师),

	征服者(HeroIcon.征服者),
	皇室卫兵(HeroIcon.皇室卫兵),

	灵月杀手(HeroIcon.灵月杀手),
	土影(HeroIcon.土影),

	阿修罗(HeroIcon.阿修罗),
	指环王(HeroIcon.指环王),

	剑魔(HeroIcon.剑魔),
	圣女(HeroIcon.圣女),

	灾厄化身(HeroIcon.灾厄化身),
	灵剪刺客(HeroIcon.灵剪刺客),

	幸运之子(HeroIcon.幸运之子),
	图书管理员(HeroIcon.图书管理员),

	黑白双子(HeroIcon.黑白双子),
	猫头鹰(HeroIcon.猫头鹰),

	巫咒王鼠(HeroIcon.巫咒王鼠),
	实验狂鼠(HeroIcon.实验狂鼠),

	解咒真人(HeroIcon.解咒真人),
	吞噬云烟(HeroIcon.吞噬云烟),

	狂战士(HeroIcon.BERSERKER),
	血法师(HeroIcon.血法师),

	时间刺客(HeroIcon.时间刺客),
	戏命师(HeroIcon.戏命师);

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
			return Messages.get(this, name() + "_desc")+"\n\n职业精通(升到21级并击杀第四个Boss以职业精通):"+Hero.职业精通提示(this,false,true);
	}

	public int icon(){
		return icon;
	}

}

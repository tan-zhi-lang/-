

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 臂铠 extends MeleeWeapon {

	{
		image = 物品表.臂铠;
		hitSoundPitch = 1.3f;

		tier = 1;
		命中=1.2f;
		最小 = 1;
		间隔= 0.8f;
		伤害= 0.5f;
		
		bones = false;
	}
	@Override
	public int 最大防御(int lvl){
		return 2 + lvl;
	}
	
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		Sai.comboStrikeAbility(hero, target, 1, 0, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 2 + 强化等级()/3 : 2;
		if (levelKnown){
			return Messages.get(this, "ability_desc", dmgBoost);
		} else {
			return Messages.get(this, "typical_ability_desc", dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		return "+" + augment.damageFactor(2 + level);
	}

}

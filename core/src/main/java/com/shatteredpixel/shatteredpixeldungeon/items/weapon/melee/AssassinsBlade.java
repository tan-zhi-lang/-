

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class AssassinsBlade extends MeleeWeapon {

	{
		image = 物品表.ASSASSINS_BLADE;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 0.9f;
		
		命中= 1.1f;
		间隔= 0.9f;
		伤害= 0.8f;
		伏击=true;
		伏击率=0.5f;
		tier = 4;
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	public boolean useTargeting(){
		return false;
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		双刃.sneakAbility(hero, target, 3, 2+ 强化等级(), this);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 2+ 强化等级());
		} else {
			return Messages.get(this, "typical_ability_desc", 2);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(2+level);
	}

}
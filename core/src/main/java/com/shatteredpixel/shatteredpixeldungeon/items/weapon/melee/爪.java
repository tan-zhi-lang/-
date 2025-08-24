

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 爪 extends MeleeWeapon {

	{
		image = 物品表.HAND_AXE;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.3f;
		拳套=true;
		延迟 = 0.25f;
		tier = 2;
	}

	@Override
	public int 最小攻击(int lvl) {
		return  1+tier +  //base
				lvl;    //level scaling
	}
	@Override
	public int 最大攻击(int lvl) {
		return  Math.round(1.33f*(tier+1) +
				lvl*(tier+1)*0.26f);
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

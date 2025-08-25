

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 镶钉手套 extends MeleeWeapon {

	{
		image = 物品表.GLOVES;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1.3f;

		tier = 1;
		间隔= 0.5f; //2x speed

		拳套=true;
		bones = false;
	}

	@Override
	public int 最小攻击(int lvl) {
		return  1+tier +  //base
				lvl;    //level scaling
	}
	@Override
	public int 最大攻击(int lvl) {
		return  Math.round(2*(tier+1)) +     //5 base, down from 10
				lvl*Math.round(0.4f*(tier+1));  //+1 per level, down from +2
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

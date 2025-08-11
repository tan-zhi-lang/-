

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
		延迟 = 0.5f; //2x speed
		
		bones = false;
	}

	@Override
	public int 最大攻击(int lvl) {
		return  Math.round(2.5f*(tier+1)) +     //5 base, down from 10
				lvl*Math.round(0.5f*(tier+1));  //+1 per level, down from +2
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		//+(3+0.75*lvl) damage, roughly +100% base damage, +100% scaling
		int dmgBoost = augment.damageFactor(3 + 强化等级());
		Sai.comboStrikeAbility(hero, target, 0, dmgBoost, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 3 + 强化等级() : 3;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", augment.damageFactor(dmgBoost));
		}
	}

	public String upgradeAbilityStat(int level){
		return "+" + augment.damageFactor(3 + level);
	}

}



package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class WarHammer extends MeleeWeapon {

	{
		image = 物品表.WAR_HAMMER;
		hitSound = Assets.Sounds.HIT_CRUSH;
		hitSoundPitch = 1f;

		tier = 5;
		ACC = 1.20f; //20% boost to accuracy
	}

	@Override
	public int 最大攻击(int lvl) {
		return  4*(tier+1) +    //24 base, down from 30
				lvl*(tier+1);   //scaling unchanged
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		//+(6+1.5*lvl) damage, roughly +40% base dmg, +45% scaling
		int dmgBoost = augment.damageFactor(6 + Math.round(1.5f* 强化等级()));
		Mace.heavyBlowAbility(hero, target, 1, dmgBoost, this);
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 6 + Math.round(1.5f*level);
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "-" + augment.damageFactor(最大攻击(level)+dmgBoost);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 6 + Math.round(1.5f* 强化等级()) : 6;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0)+dmgBoost, 最大攻击(0)+dmgBoost);
		}
	}

}

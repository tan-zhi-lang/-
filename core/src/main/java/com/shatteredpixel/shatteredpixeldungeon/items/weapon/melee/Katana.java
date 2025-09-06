

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Katana extends MeleeWeapon {

	{
		image = 物品表.KATANA;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.1f;
		命中=1.1f;
		间隔=0.9f;
		伤害=1.1f;
		tier = 4;
	}
	@Override
	public int 最大防御(int lvl){
		return 3 + lvl;
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		//+(8+2*lvl) damage, roughly +67% damage
		int dmgBoost = augment.damageFactor(8 + Math.round(2f* 强化等级()));
		配刺剑.lungeAbility(hero, target, 1, dmgBoost, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 8 + Math.round(2f* 强化等级()) : 8;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0)+dmgBoost, this.最大攻击(0)+dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 8 + Math.round(2f*level);
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "-" + augment.damageFactor(this.最大攻击(level)+dmgBoost);
	}

}

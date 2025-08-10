

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Katana extends MeleeWeapon {

	{
		image = 物品表.KATANA;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.1f;

		tier = 4;
	}

	@Override
	public int 最大攻击(int lvl) {
		return  4*(tier+1) +    //20 base, down from 25
				lvl*(tier+1);   //scaling unchanged
	}

	@Override
	public int defenseFactor( Char owner ) {
		return 3;	//3 extra defence
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		//+(8+2*lvl) damage, roughly +67% damage
		int dmgBoost = augment.damageFactor(8 + Math.round(2f*buffedLvl()));
		配刺剑.lungeAbility(hero, target, 1, dmgBoost, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 8 + Math.round(2f*buffedLvl()) : 8;
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

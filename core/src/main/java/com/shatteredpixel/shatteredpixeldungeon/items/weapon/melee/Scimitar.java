

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class Scimitar extends MeleeWeapon {

	{
		image = 物品表.SCIMITAR;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.2f;

		tier = 3;
		间隔= 0.8f; //1.25x speed
	}

	@Override
	public int 最大攻击(int lvl) {
		return  4*(tier+1) +    //16 base, down from 20
				lvl*(tier+1);   //scaling unchanged
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		beforeAbilityUsed(hero, null);
		//1 turn less as using the ability is instant
		Buff.延长(hero, SwordDance.class, 3+ 强化等级());
		hero.sprite.operate();
		hero.next();
		afterAbilityUsed(hero);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 4+ 强化等级());
		} else {
			return Messages.get(this, "typical_ability_desc", 4);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(4+level);
	}

	public static class SwordDance extends FlavourBuff {

		{
			announced = true;
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.DUEL_DANCE;
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (4 - visualcooldown()) / 4);
		}
	}

}

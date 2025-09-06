

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 冰门重盾 extends MeleeWeapon {

	{
		image = 物品表.冰门重盾;
		
		unique = true;
		bones = false;
		命中= 0.7f;
		间隔= 1.2f;
		伤害= 1.4f;
		tier = 1;
	}
	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if(!hero.heroSubClass(HeroSubClass.盾之勇者)) {
			actions.remove(AC_UNEQUIP);
		}
		return actions;
	}
	@Override
	public int 金币() {
		return 0;
	}
	@Override
	public int 最小攻击(int lvl) {
		return  Math.round(super.最小攻击(lvl)*(1+Dungeon.hero.天赋点数(Talent.冰门高攻,0.5f)));               //+2 per level, down from +4
	}
	@Override
	public int 最大攻击(int lvl) {
		return  Math.round(super.最大攻击(lvl)*(1+Dungeon.hero.天赋点数(Talent.冰门高攻)));
	}

	@Override
	public int 最大防御(int lvl){
		return 2 + lvl;
	}
	
	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		冰门重盾.guardAbility(hero, 5+ 强化等级(), this);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 5+ 强化等级());
		} else {
			return Messages.get(this, "typical_ability_desc", 5);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(5 + level);
	}

	public static void guardAbility(Hero hero, int duration, MeleeWeapon wep){
		wep.beforeAbilityUsed(hero, null);
		Buff.延长(hero, GuardTracker.class, duration).hasBlocked = false;
		hero.sprite.operate();
		hero.spendAndNext(Actor.TICK);
		wep.afterAbilityUsed(hero);
	}

	public static class GuardTracker extends FlavourBuff {

		{
			announced = true;
			type = buffType.POSITIVE;
		}

		public boolean hasBlocked = false;

		@Override
		public int icon() {
			return BuffIndicator.DUEL_GUARD;
		}

		@Override
		public void tintIcon(Image icon) {
			if (hasBlocked){
				icon.tint(0x651f66, 0.5f);
			} else {
				icon.resetColor();
			}
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (5 - visualcooldown()) / 5);
		}

		private static final String BLOCKED = "blocked";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(BLOCKED, hasBlocked);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			hasBlocked = bundle.getBoolean(BLOCKED);
		}
	}
}


package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LifeLink;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

public class LifeLinkSpell extends ClericSpell {

	public static LifeLinkSpell INSTANCE = new LifeLinkSpell();

	@Override
	public int icon() {
		return HeroIcon.LIFE_LINK;
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", 4 + 2*Dungeon.hero.天赋点数(Talent.LIFE_LINK), 30 + 5*Dungeon.hero.天赋点数(Talent.LIFE_LINK)) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	@Override
	public boolean canCast(Hero hero) {
		return super.canCast(hero)
				&& (PowerOfMany.getPoweredAlly() != null || Stasis.getStasisAlly() != null);
	}

	@Override
	public int chargeUse(Hero hero) {
		return 2;
	}

	@Override
	public void onCast(神圣法典 tome, Hero hero) {

		int duration = Math.round(6.67f + 3.33f*Dungeon.hero.天赋点数(Talent.LIFE_LINK));

		Char ally = PowerOfMany.getPoweredAlly();

		if (ally != null) {
			hero.sprite.zap(ally.pos);
			hero.sprite.parent.add(
					new Beam.HealthRay(hero.sprite.center(), ally.sprite.center()));

			Buff.延长(hero, LifeLink.class, duration).object = ally.id();
		} else {
			ally = Stasis.getStasisAlly();
			hero.sprite.operate();
			hero.sprite.parent.add(
					new Beam.HealthRay(DungeonTilemap.tileCenterToWorld(hero.pos), hero.sprite.center()));
		}

		Buff.延长(ally, LifeLink.class, duration).object = hero.id();
		Buff.延长(ally, LifeLinkSpellBuff.class, duration);

		if (ally == Stasis.getStasisAlly()){
			ally.buff(LifeLink.class).clearTime();
			ally.buff(LifeLinkSpellBuff.class).clearTime();
		}

		hero.spendAndNext(Actor.TICK);

		onSpellCast(tome, hero);

	}

	public static class LifeLinkSpellBuff extends FlavourBuff{

		{
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.HOLY_ARMOR;
		}

		@Override
		public float iconFadePercent() {
			int duration = Math.round(6.67f + 3.33f*Dungeon.hero.天赋点数(Talent.LIFE_LINK));
			return Math.max(0, (duration - visualcooldown()) / duration);
		}
	}
}

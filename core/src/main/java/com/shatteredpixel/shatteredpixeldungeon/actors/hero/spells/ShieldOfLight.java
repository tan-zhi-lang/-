

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class ShieldOfLight extends TargetedClericSpell {

	public static ShieldOfLight INSTANCE = new ShieldOfLight();

	@Override
	public int icon() {
		return HeroIcon.SHIELD_OF_LIGHT;
	}

	@Override
	public int targetingFlags() {
		return Ballistica.STOP_TARGET;
	}


	@Override
	protected void onTargetSelected(神圣法典 tome, Hero hero, Integer target) {

		if (target == null){
			return;
		}

		Char ch = Actor.findChar(target);
		if (ch == null || ch.alignment == Char.Alignment.ALLY || !Dungeon.level.heroFOV[target]){
			GLog.w(Messages.get(this, "no_target"));
			return;
		}

		QuickSlotButton.target(ch);

		Sample.INSTANCE.play(Assets.Sounds.READ);
		hero.sprite.operate(hero.pos);

		//1 turn less as the casting is instant
		Buff.延长( hero, ShieldOfLightTracker.class, 4f).object = ch.id();

		if (hero.subClass == HeroSubClass.PRIEST) {
			Buff.施加(ch, GuidingLight.Illuminated.class);
		}

		hero.busy();
		hero.sprite.operate(hero.pos);
		hero.sprite.emitter().start(Speck.factory(Speck.LIGHT), 0.15f, 6);

		Char ally = PowerOfMany.getPoweredAlly();
		if (ally != null && ally.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null){
			Buff.延长( ally, ShieldOfLightTracker.class, 3f).object = ch.id();
			ally.sprite.emitter().start(Speck.factory(Speck.LIGHT), 0.15f, 6);
		}

		onSpellCast(tome, hero);

	}

	@Override
	public String desc() {
		int min = 0;
		int max = Dungeon.hero.天赋点数(Talent.SHIELD_OF_LIGHT,5)+Dungeon.hero.最大生命(Dungeon.hero.天赋点数(Talent.SHIELD_OF_LIGHT,0.03f));
		return Messages.get(this, "desc", min, max) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	public static class ShieldOfLightTracker extends FlavourBuff {

		public int object = 0;

		private static final float DURATION = 5;

		{
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.LIGHT_SHIELD;
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

		private static final String OBJECT  = "object";

		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( OBJECT, object );
		}

		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			object = bundle.getInt( OBJECT );
		}

	}

}

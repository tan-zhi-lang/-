

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.AscendedForm;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;

public class DivineIntervention extends ClericSpell {

	public static DivineIntervention INSTANCE = new DivineIntervention();

	@Override
	public int icon() {
		return HeroIcon.DIVINE_INTERVENTION;
	}

	@Override
	public float chargeUse(Hero hero) {
		return 5;
	}

	@Override
	public boolean canCast(Hero hero) {
		return super.canCast(hero)
				&& hero.有天赋(Talent.DIVINE_INTERVENTION)
				&& hero.buff(AscendedForm.AscendBuff.class) != null
				&& !hero.buff(AscendedForm.AscendBuff.class).divineInverventionCast;
	}

	@Override
	public void onCast(神圣法典 tome, Hero hero) {

		Sample.INSTANCE.play(Assets.Sounds.CHARGEUP, 1, 1.2f);
		hero.sprite.operate(hero.pos);

		for (Char ch : Actor.chars()){
			if (ch.alignment == Char.Alignment.ALLY && ch != hero){
				Buff.施加(ch, DivineShield.class).设置(100 + 50*hero.天赋点数(Talent.DIVINE_INTERVENTION));
				new Flare(6, 32).color(0xFFFF00, true).show(ch.sprite, 2f);
			}
		}

		hero.spendAndNext( 1f );
		onSpellCast(tome, hero);

		//we apply buffs here so that the 5 charge cost and shield boost do not stack
		hero.buff(AscendedForm.AscendBuff.class).设置(100 + 50*hero.天赋点数(Talent.DIVINE_INTERVENTION));
		new Flare(6, 32).color(0xFFFF00, true).show(hero.sprite, 2f);

		hero.buff(AscendedForm.AscendBuff.class).divineInverventionCast = true;
		hero.buff(AscendedForm.AscendBuff.class).extend(2+hero.天赋点数(Talent.DIVINE_INTERVENTION));

	}

	@Override
	public String desc() {
		int shield = 100 + 50*Dungeon.hero.天赋点数(Talent.DIVINE_INTERVENTION);
		int leftBonus = 2+Dungeon.hero.天赋点数(Talent.DIVINE_INTERVENTION);
		return Messages.get(this, "desc", shield, leftBonus) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	public static class DivineShield extends ShieldBuff{

		{
			shieldUsePriority = 1;
		}

		@Override
		public boolean act() {

			if (Dungeon.hero == null || Dungeon.hero.buff(AscendedForm.AscendBuff.class) == null){
				detach();
			}

			spend(TICK);
			return true;
		}

		@Override
		public int 护盾量() {
			if (Dungeon.hero == null || Dungeon.hero.buff(AscendedForm.AscendBuff.class) == null){
				return 0;
			}
			return super.护盾量();
		}

		@Override
		public void fx(boolean on) {
			if (on) target.sprite.add(CharSprite.State.SHIELDED);
			else    target.sprite.remove(CharSprite.State.SHIELDED);
		}
	}
}

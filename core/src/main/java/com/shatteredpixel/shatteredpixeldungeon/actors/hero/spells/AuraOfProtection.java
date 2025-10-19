

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;

public class AuraOfProtection extends ClericSpell {

	public static AuraOfProtection INSTANCE = new AuraOfProtection();

	@Override
	public int icon() {
		return HeroIcon.AURA_OF_PROTECTION;
	}

	@Override
	public String desc() {
		int dmgReduction = Dungeon.hero.天赋点数(Talent.AURA_OF_PROTECTION,12);
		int glyphPow = Dungeon.hero.天赋点数(Talent.AURA_OF_PROTECTION,30);
		return Messages.get(this, "desc", dmgReduction, glyphPow) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	@Override
	public int chargeUse(Hero hero) {
		return 2;
	}

	@Override
	public void onCast(神圣法典 tome, Hero hero) {

		Buff.施加(hero,AuraBuff.class, AuraBuff.DURATION);

		Sample.INSTANCE.play(Assets.Sounds.READ);

		hero.spend( 1f );
		hero.busy();
		hero.sprite.operate();

		onSpellCast(tome, hero);

	}

	public static class AuraBuff extends FlavourBuff {

		public static float DURATION = 20f;

		private Emitter particles;

		{
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.PROT_AURA;
		}

		@Override
		public void fx(boolean on) {
			if (on && (particles == null || particles.parent == null)){
				particles = target.sprite.emitter(); //emitter is much bigger than char so it needs to manage itself
				particles.pos(target.sprite, -32, -32, 80, 80);
				particles.fillTarget = false;
				particles.pour(Speck.factory(Speck.LIGHT), 0.02f);
			} else if (!on && particles != null){
				particles.on = false;
			}
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

	}

}

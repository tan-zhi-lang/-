

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class BlessSpell extends TargetedClericSpell {

	public static final BlessSpell INSTANCE = new BlessSpell();

	@Override
	public int icon() {
		return HeroIcon.BLESS;
	}

	@Override
	public int targetingFlags(){
		return -1; //auto-targeting behaviour is often wrong, so we don't use it
	}

	@Override
	protected void onTargetSelected(神圣法典 tome, Hero hero, Integer target) {
		if (target == null){
			return;
		}

		Char ch = Actor.findChar(target);
		if (ch == null || !Dungeon.level.heroFOV[target]){
			GLog.w(Messages.get(this, "no_target"));
			return;
		}

		Sample.INSTANCE.play(Assets.Sounds.TELEPORT);

		affectChar(hero, ch);

		if (ch == hero){
			hero.busy();
			hero.sprite.operate(ch.pos);
			hero.spend( 1f );
		} else {
			hero.sprite.zap(ch.pos);
			hero.spendAndNext( 1f );
		}

		Char ally = PowerOfMany.getPoweredAlly();
		if (ally != null && ally.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null){
			if (ch == hero){
				affectChar(hero, ally); //if cast on hero, duplicate to ally
			} else if (ally == ch) {
				affectChar(hero, hero); //if cast on ally, duplicate to hero
			}
		}

		onSpellCast(tome, hero);
	}

	private void affectChar(Hero hero, Char ch){
		new Flare(6, 32).color(0xFFFF00, true).show(ch.sprite, 2f);
		Buff.延长(ch, Bless.class, hero.天赋点数(Talent.BLESS,5));
		if (ch == hero){
			Buff.施加(ch, Barrier.class).设置(hero.天赋点数(Talent.BLESS,5)+hero.最大生命(hero.天赋点数(Talent.BLESS,0.05f)));
			ch.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(hero.天赋点数(Talent.BLESS,5)), FloatingText.SHIELDING );
		} else {
			int totalHeal = hero.天赋点数(Talent.BLESS,5)+ch.最大生命(hero.天赋点数(Talent.BLESS,0.05f));
			if (ch.最大生命 - ch.生命 < totalHeal){
				int barrier = totalHeal - (ch.最大生命 - ch.生命);
				barrier = Math.max(barrier, 0);
				if (ch.生命 != ch.最大生命) {
					ch.生命 = ch.最大生命;
					ch.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(totalHeal - barrier), FloatingText.HEALING);
				}
				if (barrier > 0) {
					Buff.施加(ch, Barrier.class).设置(barrier);
					ch.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(barrier), FloatingText.SHIELDING);
				}
			} else {
				ch.生命 = ch.生命 + totalHeal;
				ch.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(totalHeal), FloatingText.HEALING );
			}
		}

		if (ch.alignment != Char.Alignment.ALLY && hero.subClass == HeroSubClass.PRIEST){
			Buff.施加(ch, GuidingLight.Illuminated.class);
		}
	}

	public String desc(){
		int talentLvl = Dungeon.hero.天赋点数(Talent.BLESS);
		return Messages.get(this, "desc", 2+4*talentLvl, 5+5*talentLvl, 5+5*talentLvl, 5+5*talentLvl) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

}

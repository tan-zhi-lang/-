

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class LayOnHands extends TargetedClericSpell {

	public static LayOnHands INSTANCE = new LayOnHands();

	@Override
	public int icon() {
		return HeroIcon.LAY_ON_HANDS;
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", Dungeon.hero.天赋生命力(Talent.LAY_ON_HANDS,0.7f)) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	@Override
	public int targetingFlags(){
		return -1; //auto-targeting behaviour is often wrong, so we don't use it
	}

	@Override
	protected void onTargetSelected(神圣法典 tome, Hero hero, Integer target) {
		if (target == null) {
			return;
		}

		if (Dungeon.level.distance(hero.pos, target) > 1){
			GLog.w(Messages.get(this, "invalid_target"));
			return;
		}

		Char ch = Actor.findChar(target);
		if (ch == null){
			GLog.w(Messages.get(this, "no_target"));
			return;
		}

		Sample.INSTANCE.play(Assets.Sounds.TELEPORT);

		affectChar(hero, ch);

		if (ch == hero){
			hero.sprite.operate(ch.pos);
			hero.next();
		} else {
			hero.sprite.zap(ch.pos);
			hero.next();
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
		float totalHeal = hero.天赋生命力(Talent.LAY_ON_HANDS,0.7f);
		float totalBarrier = 0;
		if (ch == hero){
			Barrier barrier = Buff.施加(ch, Barrier.class);
			totalBarrier = totalHeal;
			totalBarrier = Math.min(3*totalHeal - barrier.护盾量(), totalBarrier);
			totalBarrier = Math.max(0, totalBarrier);
			Buff.施加(ch, Barrier.class).增加(totalBarrier);
		} else {
			if (ch.最大生命 - ch.生命 < totalHeal){
				totalBarrier = totalHeal - (ch.最大生命 - ch.生命);
				ch.回满血();
				if (totalBarrier > 0) {
					Barrier barrier = Buff.施加(ch, Barrier.class);
					totalBarrier = Math.min(3 * totalHeal - barrier.护盾量(), totalBarrier);
					totalBarrier = Math.max(0, totalBarrier);
					if (totalBarrier > 0) {
						barrier.增加(totalBarrier);
					}
				}
			} else {
				ch.回血(totalHeal);
			}
		}
	}
}

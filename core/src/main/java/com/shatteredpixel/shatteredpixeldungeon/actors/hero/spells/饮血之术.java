

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 饮血之术 extends 目标巫术 {

	public static final 饮血之术 INSTANCE = new 饮血之术();

	@Override
	public int icon() {
		return HeroIcon.饮血之术;
	}

	@Override
	protected void onTargetSelected(灵月法杖 tome, Hero hero, Integer target) {
		if (target == null){
			return;
		}

		Ballistica aim = new Ballistica(hero.pos, target, targetingFlags());


		if (Actor.findChar(aim.collisionPos) != null) {
			QuickSlotButton.target(Actor.findChar(aim.collisionPos));
		} else {
			QuickSlotButton.target(Actor.findChar(target));
		}
		Sample.INSTANCE.play(Assets.Sounds.HIT_MAGIC,1,Random.Float(0.87f,1.15f));

		hero.sprite.zap(target);
		MagicMissile.boltFromChar(hero.sprite.parent, MagicMissile.SHADOW, hero.sprite, aim.collisionPos, new Callback() {
			@Override
			public void call() {

				Char ch = Actor.findChar( aim.collisionPos );
				if (ch != null&&!ch.满血()&&!(ch.properties.contains(Char.Property.MINIBOSS)||ch.properties.contains(Char.Property.BOSS_MINION)||ch.properties.contains(Char.Property.BOSS))) {
					ch.生命=1;
					Buff.延长(ch, Invulnerability.class, Invulnerability.DURATION/2);
					hero.回血(hero.已损失生命(hero.天赋点数(Talent.饮血之术,0.15f)));
					
				} else {
					Dungeon.level.pressCell(aim.collisionPos);
				}

				onSpellCast(tome, hero);

			}
		});
	}

	

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.已损失生命(Dungeon.hero.天赋点数(Talent.饮血之术,0.15f)));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

}

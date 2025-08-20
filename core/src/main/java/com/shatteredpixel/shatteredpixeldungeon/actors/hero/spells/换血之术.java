

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 换血之术 extends 目标巫术 {

	public static final 换血之术 INSTANCE = new 换血之术();

	@Override
	public int icon() {
		return HeroIcon.换血之术;
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

		hero.busy();
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
		hero.sprite.zap(target);
		MagicMissile.boltFromChar(hero.sprite.parent, MagicMissile.SHADOW, hero.sprite, aim.collisionPos, new Callback() {
			@Override
			public void call() {

				Char ch = Actor.findChar( aim.collisionPos );
				if (ch != null) {
					Buff.施加( ch, 流血.class ).set(ch.最大生命(Dungeon.hero.天赋点数(Talent.换血之术,0.5f)));
					Buff.施加( hero, 流血.class ).set(hero.最大生命(0.6f-Dungeon.hero.天赋点数(Talent.换血之术,0.15f)));
					Sample.INSTANCE.play(Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f));
				} else {
					Dungeon.level.pressCell(aim.collisionPos);
				}

				hero.spend( 1f );
				hero.next();

				onSpellCast(tome, hero);

			}
		});
	}


	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.天赋点数(Talent.换血之术,50),60-Dungeon.hero.天赋点数(Talent.换血之术,15));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

}

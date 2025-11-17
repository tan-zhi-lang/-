

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class GuidingLight extends TargetedClericSpell {

	public static final GuidingLight INSTANCE = new GuidingLight();

	@Override
	public int icon() {
		return HeroIcon.GUIDING_LIGHT;
	}

	@Override
	protected void onTargetSelected(神圣法典 tome, Hero hero, Integer target) {
		if (target == null){
			return;
		}

		Ballistica aim = new Ballistica(hero.pos, target, targetingFlags());

		if (Actor.findChar( aim.collisionPos ) == hero){
			GLog.i( Messages.get(Wand.class, "self_target") );
			return;
		}

		if (Actor.findChar(aim.collisionPos) != null) {
			QuickSlotButton.target(Actor.findChar(aim.collisionPos));
		} else {
			QuickSlotButton.target(Actor.findChar(target));
		}

		hero.busy();
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
		hero.sprite.zap(target);
		MagicMissile.boltFromChar(hero.sprite.parent, MagicMissile.LIGHT_MISSILE, hero.sprite, aim.collisionPos, new Callback() {
			@Override
			public void call() {

				Char ch = Actor.findChar( aim.collisionPos );
				if (ch != null) {
					ch.受伤时(Random.NormalIntRange(2, 8), GuidingLight.this);
					Sample.INSTANCE.play(Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f));
					ch.sprite.burst(0xFFFFFF44, 3);
					if (ch.isAlive()){
						Buff.施加(ch, Illuminated.class);
						Buff.施加(ch, WasIlluminatedTracker.class);
					}
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
	public int chargeUse(Hero hero) {
		return 1;
	}

	public String desc(){
		String desc = Messages.get(this, "desc",2,8);
		if (Dungeon.hero.subClass == HeroSubClass.PRIEST){
			desc += "\n\n" + Messages.get(this, "desc_priest",Dungeon.hero.生命力(0.5f),Dungeon.hero.生命力(4f));
		}
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	public static class Illuminated extends Buff {

		{
			type = buffType.NEGATIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.ILLUMINATED;
		}

		@Override
		public void fx(boolean on) {
			if (on) target.sprite.add(CharSprite.State.ILLUMINATED);
			else target.sprite.remove(CharSprite.State.ILLUMINATED);
		}

		@Override
		public String desc() {
			String desc = super.desc();

			if (Dungeon.hero.subClass == HeroSubClass.PRIEST){
				desc += "\n\n" + Messages.get(this, "desc_priest");
			} else if (Dungeon.hero.heroClass != HeroClass.CLERIC){
				desc += "\n\n" + Messages.get(this, "desc_generic");
			}

			return desc;
		}
	}

	public static class WasIlluminatedTracker extends Buff {}
}

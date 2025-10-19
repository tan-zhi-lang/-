

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MissileSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class HolyLance extends TargetedClericSpell {

	public static final HolyLance INSTANCE = new HolyLance();

	@Override
	public int icon() {
		return HeroIcon.HOLY_LANCE;
	}

	@Override
	public String desc() {
		int min = Dungeon.hero.天赋生命力(Talent.HOLY_LANCE,0.6f);
		int max = Dungeon.hero.天赋生命力(Talent.HOLY_LANCE,1.2f);
		return Messages.get(this, "desc", min, max) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	@Override
	public boolean canCast(Hero hero) {
		return super.canCast(hero)
				&& hero.buff(LanceCooldown.class) == null;
	}

	@Override
	public int chargeUse(Hero hero) {
		return 2;
	}

	@Override
	public int targetingFlags() {
		return Ballistica.PROJECTILE;
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

		hero.sprite.zap( target );
		hero.busy();

		Sample.INSTANCE.play(Assets.Sounds.ZAP);

		Char enemy = Actor.findChar(aim.collisionPos);
		if (enemy != null) {
			((MissileSprite) hero.sprite.parent.recycle(MissileSprite.class)).
					reset(hero.sprite,
							enemy.sprite,
							new HolyLanceVFX(),
							new Callback() {
								@Override
								public void call() {
									int min = Dungeon.hero.天赋生命力(Talent.HOLY_LANCE,0.6f);
									int max = Dungeon.hero.天赋生命力(Talent.HOLY_LANCE,1.2f);
									if (Char.hasProp(enemy, Char.Property.UNDEAD) || Char.hasProp(enemy, Char.Property.DEMONIC)){
										min = max;
									}
									enemy.受伤时(Random.NormalIntRange(min, max), HolyLance.this);
									Sample.INSTANCE.play( Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.8f, 1f) );
									Sample.INSTANCE.play( Assets.Sounds.HIT_STAB, 1, Random.Float(0.8f, 1f) );

									if (enemy.isActive()){
										Buff.施加(enemy, GuidingLight.Illuminated.class);
									}

									enemy.sprite.burst(0xFFFFFFFF, 10);
									hero.spendAndNext(1f);
									onSpellCast(tome, hero);
									FlavourBuff.施加(hero, LanceCooldown.class, 30f);
								}
							});
		} else {
			((MissileSprite) hero.sprite.parent.recycle(MissileSprite.class)).
					reset(hero.sprite,
							target,
							new HolyLanceVFX(),
							new Callback() {
								@Override
								public void call() {
									Splash.at(target, 0xFFFFFFFF, 10);
									Dungeon.level.pressCell(aim.collisionPos);
									hero.spendAndNext(1f);
									onSpellCast(tome, hero);
									FlavourBuff.施加(hero, LanceCooldown.class, 30f);
								}
							});
		}

	}

	public static class HolyLanceVFX extends Item {

		{
			image = 物品表.THROWING_SPIKE;
		}

		@Override
		public ItemSprite.Glowing glowing() {
			return new ItemSprite.Glowing(0xFFFFFF, 0.1f);
		}

		@Override
		public Emitter emitter() {
			Emitter emitter = new Emitter();
			emitter.pos( 5, 5, 0, 0);
			emitter.fillTarget = false;
			emitter.pour(SparkParticle.FACTORY, 0.025f);
			return emitter;
		}
	}

	public static class LanceCooldown extends FlavourBuff {

		@Override
		public int icon() {
			return BuffIndicator.TIME;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0.67f, 0.67f, 0);
		}

		public float iconFadePercent() { return Math.max(0, visualcooldown() / 30); }
	}
}

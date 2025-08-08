

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MirrorSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.TargetHealthIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.noosa.tweeners.Delayer;
import com.watabou.utils.Callback;

public class Feint extends ArmorAbility {

	{
		baseChargeUse = 50;
	}

	@Override
	public int icon() {
		return HeroIcon.FEINT;
	}

	public boolean useTargeting(){
		return false;
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	public int targetedPos(Char user, int dst) {
		return dst;
	}

	@Override
	protected void activate(ClassArmor armor, Hero hero, Integer target) {
		if (target == null){
			return;
		}

		if (!Dungeon.level.adjacent(hero.pos, target)){
			GLog.w(Messages.get(this, "too_far"));
			return;
		}

		if (Dungeon.hero.rooted){
			PixelScene.shake( 1, 1f );
			GLog.w(Messages.get(this, "bad_location"));
			return;
		}

		if (Dungeon.level.solid[target] || Actor.findChar(target) != null){
			GLog.w(Messages.get(this, "bad_location"));
			return;
		}

		hero.busy();
		Sample.INSTANCE.play(Assets.Sounds.MISS);
		hero.sprite.jump(hero.pos, target, 0, 0.1f, new Callback() {
			@Override
			public void call() {
				if (Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR) {
					Door.leave( hero.pos );
				}
				hero.pos = target;
				Dungeon.level.occupyCell(hero);
				Invisibility.dispel();
				hero.spendAndNext(1f);
			}
		});

		AfterImage image = new AfterImage();
		image.pos = hero.pos;
		GameScene.add(image, 1);

		int imageAttackPos;
		Char enemyTarget = TargetHealthIndicator.instance.target();
		if (enemyTarget != null && enemyTarget.alignment == Char.Alignment.ENEMY){
			imageAttackPos = enemyTarget.pos;
		} else {
			imageAttackPos = image.pos + (image.pos - target);
		}
		//do a purely visual attack
		hero.sprite.parent.add(new Delayer(0f){
			@Override
			protected void onComplete() {
				image.sprite.attack(imageAttackPos, new Callback() {
					@Override
					public void call() {
						//do nothing, attack is purely visual
					}
				});
			}
		});

		for (Mob m : Dungeon.level.mobs.toArray( new Mob[0] )){
			if ((m.isTargeting(hero) && m.state == m.HUNTING) ||
					(m.alignment == Char.Alignment.ENEMY && m.state != m.PASSIVE && Dungeon.level.distance(m.pos, image.pos) <= 2)){
				m.aggro(image);
			}
		}

		armor.charge -= chargeUse(hero);
		Item.updateQuickslot();
	}

	@Override
	public Talent[] talents() {
		return new Talent[]{Talent.FEIGNED_RETREAT, Talent.EXPOSE_WEAKNESS, Talent.COUNTER_ABILITY, Talent.HEROIC_ENERGY};
	}

	public static class AfterImage extends Mob {

		{
			spriteClass = AfterImageSprite.class;
			defenseSkill = 0;

			properties.add(Property.IMMOVABLE);

			alignment = Alignment.ALLY;
			state = PASSIVE;

			生命 = 最大生命 = 1;

			//fades just before the hero's next action
			actPriority = Actor.HERO_PRIO+1;
		}

		@Override
		public boolean canInteract(Char c) {
			return false;
		}

		@Override
		protected boolean act() {
			destroy();
			sprite.die();
			return true;
		}

		@Override
		public void damage( int dmg, Object src ) {

		}

		@Override
		public int defenseSkill(Char enemy) {
			if (enemy.alignment == Alignment.ENEMY) {
				if (enemy instanceof Mob) {
					((Mob) enemy).clearEnemy();
				}
				Buff.施加(enemy, FeintConfusion.class, 1);
				if (enemy.sprite != null) enemy.sprite.showLost();
				if (Dungeon.hero.有天赋(Talent.FEIGNED_RETREAT)) {
					Buff.延长(Dungeon.hero, Haste.class, 2f * Dungeon.hero.天赋点数(Talent.FEIGNED_RETREAT));
				}
				if (Dungeon.hero.有天赋(Talent.EXPOSE_WEAKNESS)) {
					Buff.延长(enemy, Vulnerable.class, 2f * Dungeon.hero.天赋点数(Talent.EXPOSE_WEAKNESS));
					Buff.延长(enemy, Weakness.class, 2f * Dungeon.hero.天赋点数(Talent.EXPOSE_WEAKNESS));
				}
				if (Dungeon.hero.有天赋(Talent.COUNTER_ABILITY)) {
					Buff.延长(Dungeon.hero, Talent.CounterAbilityTacker.class, 3f);
				}
			}
			return 0;
		}

		@Override
		public boolean add( Buff buff ) {
			return false;
		}

		{
			immunities.addAll(new BlobImmunity().immunities());
		}

		@Override
		public CharSprite sprite() {
			CharSprite s = super.sprite();
			((AfterImageSprite)s).updateArmor();
			return s;
		}

		public static class FeintConfusion extends FlavourBuff {

		}

		public static class AfterImageSprite extends MirrorSprite {
			@Override
			public void updateArmor() {
				updateArmor(6); //we can assume heroic armor
			}

			@Override
			public void resetColor() {
				super.resetColor();
				alpha(0.6f);
			}

			@Override
			public void die() {
				//don't interrupt current animation to start fading
				//this ensures the fake attack animation plays
				if (parent != null) {
					parent.add( new AlphaTweener( this, 0, 3f ) {
						@Override
						protected void onComplete() {
							AfterImageSprite.this.killAndErase();
						}
					} );
				}
			}
		}

	}
}

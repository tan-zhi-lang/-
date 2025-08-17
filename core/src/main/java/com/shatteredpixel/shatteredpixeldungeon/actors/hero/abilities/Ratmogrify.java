

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.TargetHealthIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashSet;

public class Ratmogrify extends ArmorAbility {

	{
		baseChargeUse = 50f;
	}

	//this is sort of hacky, but we need it to know when to use alternate name/icon for heroic energy
	public static boolean useRatroicEnergy = false;

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

		Char ch = Actor.findChar(target);

		if (ch == null || !Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(this, "no_target"));
			return;
		} else if (ch == hero){
			if (!hero.有天赋(Talent.RATFORCEMENTS)){
				GLog.w(Messages.get(this, "self_target"));
				return;
			} else {
				ArrayList<Integer> spawnPoints = new ArrayList<>();

				for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
					int p = hero.pos + PathFinder.NEIGHBOURS8[i];
					if (Actor.findChar( p ) == null && Dungeon.level.passable[p]) {
						spawnPoints.add( p );
					}
				}

				int ratsToSpawn = hero.天赋点数(Talent.RATFORCEMENTS);

				while (ratsToSpawn > 0 && spawnPoints.size() > 0) {
					int index = Random.index( spawnPoints );

					Rat rat = new Rat();
					rat.alignment = Char.Alignment.ALLY;
					rat.state = rat.HUNTING;
					Buff.施加(rat, AscensionChallenge.AscensionBuffBlocker.class);
					GameScene.add( rat );
					ScrollOfTeleportation.appear( rat, spawnPoints.get( index ) );

					spawnPoints.remove( index );
					ratsToSpawn--;
				}

			}
		} else if (ch.alignment != Char.Alignment.ENEMY || !(ch instanceof Mob) || ch instanceof Rat){
			GLog.w(Messages.get(this, "cant_transform"));
			return;
		} else if (ch instanceof TransmogRat){
			if (((TransmogRat) ch).allied || !hero.有天赋(Talent.RATLOMACY)){
				GLog.w(Messages.get(this, "cant_transform"));
				return;
			} else {
				((TransmogRat) ch).makeAlly();
				ch.sprite.emitter().start(Speck.factory(Speck.HEART), 0.2f, 5);
				Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
				if (hero.天赋点数(Talent.RATLOMACY) > 1){
					Buff.施加(ch, Adrenaline.class, 2*(hero.天赋点数(Talent.RATLOMACY)-1));
				}
			}
		} else if (Char.hasProp(ch, Char.Property.MINIBOSS) || Char.hasProp(ch, Char.Property.BOSS)){
			GLog.w(Messages.get(this, "too_strong"));
			return;
		} else {
			TransmogRat rat = new TransmogRat();
			rat.setup((Mob)ch);
			rat.pos = ch.pos;

			//preserve some buffs
			HashSet<Buff> persistentBuffs = new HashSet<>();
			for (Buff b : ch.buffs()){
				if (b.revivePersists){
					persistentBuffs.add(b);
				}
			}

			Actor.remove( ch );
			ch.sprite.killAndErase();
			Dungeon.level.mobs.remove(ch);

			for (Buff b : persistentBuffs){
				ch.add(b);
			}

			GameScene.add(rat);

			TargetHealthIndicator.instance.target(null);
			CellEmitter.get(rat.pos).burst(Speck.factory(Speck.WOOL), 4);
			Sample.INSTANCE.play(Assets.Sounds.PUFF);

			//for rare cases where a buff was keeping a mob alive (e.g. gnoll brute rage)
			if (!rat.isAlive()){
				rat.死亡时(this);
			} else {
				Dungeon.level.occupyCell(rat);
			}
		}

		armor.charge -= chargeUse(hero);
		armor.updateQuickslot();
		Invisibility.dispel();
		hero.spendAndNext(Actor.TICK);

	}

	@Override
	public int icon() {
		return HeroIcon.RATMOGRIFY;
	}

	@Override
	public Talent[] talents() {
		return new Talent[]{ Talent.RATSISTANCE, Talent.RATLOMACY, Talent.RATFORCEMENTS, Talent.HEROIC_ENERGY};
	}

	public static class TransmogRat extends Mob {

		{
			spriteClass = RatSprite.class;

			//always false, as we derive stats from what we are transmogging from (which was already added)
			firstAdded = false;
		}

		private Mob original;
		private boolean allied;

		public void setup(Mob original) {
			this.original = original;
			original.clearTime();

			生命 = original.生命;
			最大生命 = original.最大生命;

			defenseSkill = original.defenseSkill;

			经验 = original.经验;
			最大等级 = original.最大等级;

			if (original.state == original.SLEEPING) {
				state = SLEEPING;
			} else if (original.state == original.HUNTING) {
				state = HUNTING;
			} else {
				state = WANDERING;
			}

		}

		public Mob getOriginal(){
			if (original != null) {
				original.生命 = 生命;
				original.pos = pos;
			}
			return original;
		}

		private float timeLeft = 6f;

		@Override
		protected boolean act() {
			if (timeLeft <= 0){
				Mob original = getOriginal();
				this.original = null;
				GameScene.add(original);

				经验 = 0;
				destroy();
				sprite.killAndErase();
				CellEmitter.get(original.pos).burst(Speck.factory(Speck.WOOL), 4);
				Sample.INSTANCE.play(Assets.Sounds.PUFF);
				return true;
			} else {
				return super.act();
			}
		}

		@Override
		protected void spend(float time) {
			if (!allied) timeLeft -= time;
			super.spend(time);
		}

		public void makeAlly() {
			allied = true;
			alignment = Alignment.ALLY;
			timeLeft = Float.POSITIVE_INFINITY;
			Bestiary.setSeen(original.getClass());
			Bestiary.countEncounter(original.getClass());
		}

		public int 最大命中(Char target) {
			return original.最大命中(target);
		}

		public int 防御() {
			return original.防御();
		}

		@Override
		public int 攻击() {
			int damage = original.攻击();
			if (!allied && Dungeon.hero.有天赋(Talent.RATSISTANCE)){
				damage *= Math.pow(0.9f, Dungeon.hero.天赋点数(Talent.RATSISTANCE));
			}
			return damage;
		}

		@Override
		public float attackDelay() {
			return original.attackDelay();
		}

		@Override
		public void rollToDropLoot() {
			original.pos = pos;
			original.rollToDropLoot();
		}

		@Override
		public void destroy() {
			super.destroy();
			if (alignment == Alignment.ENEMY && original != null) {
				Bestiary.setSeen(original.getClass());
				Bestiary.countEncounter(original.getClass());
			}
		}

		@Override
		public String name() {
			return Messages.get(this, "name", original.name());
		}

		{
			immunities.add(AllyBuff.class);
		}

		private static final String ORIGINAL = "original";
		private static final String ALLIED = "allied";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(ORIGINAL, original);
			bundle.put(ALLIED, allied);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);

			original = (Mob) bundle.get(ORIGINAL);
			defenseSkill = original.defenseSkill;
			经验 = original.经验;

			allied = bundle.getBoolean(ALLIED);
			if (allied) alignment = Alignment.ALLY;
		}
	}
}

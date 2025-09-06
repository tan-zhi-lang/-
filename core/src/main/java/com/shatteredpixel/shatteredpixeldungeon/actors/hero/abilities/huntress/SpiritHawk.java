

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.DirectableAlly;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class SpiritHawk extends ArmorAbility {

	@Override
	public String targetingPrompt() {
		if (getHawk() == null) {
			return super.targetingPrompt();
		} else {
			return Messages.get(this, "prompt");
		}
	}

	@Override
	public boolean useTargeting(){
		return false;
	}

	{
		baseChargeUse = 35f;
	}

	@Override
	public float chargeUse(Hero hero) {
		if (getHawk() == null) {
			return super.chargeUse(hero);
		} else {
			return 0;
		}
	}

	@Override
	protected void activate(ClassArmor armor, Hero hero, Integer target) {
		HawkAlly ally = getHawk();

		if (ally != null){
			if (target == null){
				return;
			} else {
				ally.directTocell(target);
			}
		} else {
			ArrayList<Integer> spawnPoints = new ArrayList<>();
			for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
				int p = hero.pos + PathFinder.NEIGHBOURS8[i];
				if (Actor.findChar(p) == null && (Dungeon.level.passable[p] || Dungeon.level.avoid[p])) {
					spawnPoints.add(p);
				}
			}

			if (!spawnPoints.isEmpty()){
				armor.charge -= chargeUse(hero);
				armor.updateQuickslot();

				ally = new HawkAlly();
				ally.pos = Random.element(spawnPoints);
				GameScene.add(ally);

				ScrollOfTeleportation.appear(ally, ally.pos);
				Dungeon.observe();

				Invisibility.dispel();
				hero.spendAndNext(Actor.TICK);

			} else {
				GLog.w(Messages.get(this, "no_space"));
			}
		}

	}

	@Override
	public int icon() {
		return HeroIcon.SPIRIT_HAWK;
	}

	@Override
	public Talent[] talents() {
		return new Talent[]{Talent.EAGLE_EYE, Talent.GO_FOR_THE_EYES, Talent.SWIFT_SPIRIT, Talent.HEROIC_ENERGY};
	}

	private static HawkAlly getHawk(){
		for (Char ch : Actor.chars()){
			if (ch instanceof HawkAlly){
				return (HawkAlly) ch;
			}
		}
		return null;
	}

	public static class HawkAlly extends DirectableAlly {

		{
			spriteClass = HawkSprite.class;

			生命 = 最大生命 = 10;
			defenseSkill = 60;

			flying = true;
			if (Dungeon.hero()) {
				viewDistance = (int) GameMath.gate(6, 6 + Dungeon.hero.天赋点数(Talent.EAGLE_EYE), 8);
				baseSpeed = 2f + Dungeon.hero.天赋点数(Talent.SWIFT_SPIRIT) / 2f;
			} else {
				viewDistance = 6;
				baseSpeed = 2f;
			}
			attacksAutomatically = false;

			immunities.addAll(new BlobImmunity().immunities());
			immunities.add(AllyBuff.class);
		}

		@Override
		public int 最大命中(Char target) {
			return 60;
		}

		private int dodgesUsed = 0;
		private float timeRemaining = 100f;

		@Override
		public int 最大闪避(Char enemy) {
			if (Dungeon.hero.天赋(Talent.SWIFT_SPIRIT)&&
					dodgesUsed < 2*Dungeon.hero.天赋点数(Talent.SWIFT_SPIRIT)) {
				dodgesUsed++;
				return Char.INFINITE_EVASION;
			}
			return super.最大闪避(enemy);
		}

		@Override
		public int 攻击() {
			return Random.NormalIntRange(5, 10);
		}

		@Override
		public int 攻击时(Char enemy, int damage) {
			damage = super.攻击时( enemy, damage );
			switch (Dungeon.hero.天赋点数(Talent.GO_FOR_THE_EYES)){
				case 1:
					Buff.延长( enemy, Blindness.class, 2);
					break;
				case 2:
					Buff.延长( enemy, Blindness.class, 5);
					break;
				case 3:
					Buff.延长( enemy, Blindness.class, 5);
					Buff.延长( enemy, Cripple.class, 2);
					break;
				case 4:
					Buff.延长( enemy, Blindness.class, 5);
					Buff.延长( enemy, Cripple.class, 5);
					break;
				default:
					//do nothing
			}

			return damage;
		}

		@Override
		protected boolean act() {
			if (timeRemaining <= 0){
				死亡时(null);
				Dungeon.hero.interrupt();
				return true;
			}
			viewDistance = 6+Dungeon.hero.天赋点数(Talent.EAGLE_EYE);
			baseSpeed = 2f + Dungeon.hero.天赋点数(Talent.SWIFT_SPIRIT)/2f;
			boolean result = super.act();
			Dungeon.level.updateFieldOfView( this, fieldOfView );
			GameScene.updateFog(pos, viewDistance+(int)Math.ceil(移速()));
			return result;
		}

		@Override
		public void 死亡时(Object cause) {
			flying = false;
			super.死亡时(cause);
		}

		@Override
		protected void spend(float time) {
			super.spend(time);
			timeRemaining -= time;
		}

		@Override
		public void destroy() {
			super.destroy();
			Dungeon.observe();
			GameScene.updateFog();
		}

		@Override
		public void defendPos(int cell) {
			GLog.i(Messages.get(this, "direct_defend"));
			super.defendPos(cell);
		}

		@Override
		public void followHero() {
			GLog.i(Messages.get(this, "direct_follow"));
			super.followHero();
		}

		@Override
		public void targetChar(Char ch) {
			GLog.i(Messages.get(this, "direct_attack"));
			super.targetChar(ch);
		}

		@Override
		public String description() {
			String message = Messages.get(this, "desc", (int)timeRemaining);
			if (Actor.chars().contains(this)){
				message += "\n\n" + Messages.get(this, "desc_remaining", (int)timeRemaining);
				if (dodgesUsed < 2*Dungeon.hero.天赋点数(Talent.SWIFT_SPIRIT)){
					message += "\n" + Messages.get(this, "desc_dodges", (2*Dungeon.hero.天赋点数(Talent.SWIFT_SPIRIT) - dodgesUsed));
				}
			}
			return message;
		}

		private static final String DODGES_USED     = "dodges_used";
		private static final String TIME_REMAINING  = "time_remaining";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(DODGES_USED, dodgesUsed);
			bundle.put(TIME_REMAINING, timeRemaining);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			dodgesUsed = bundle.getInt(DODGES_USED);
			timeRemaining = bundle.getFloat(TIME_REMAINING);
		}
	}

	public static class HawkSprite extends MobSprite {

		public HawkSprite() {
			super();

			texture( Assets.Sprites.SPIRIT_HAWK );

			TextureFilm frames = new TextureFilm( texture, 15, 15 );

			int c = 0;

			idle = new Animation( 6, true );
			idle.frames( frames, 0, 1 );

			run = new Animation( 8, true );
			run.frames( frames, 0, 1 );

			attack = new Animation( 12, false );
			attack.frames( frames, 2, 3, 0, 1 );

			die = new Animation( 12, false );
			die.frames( frames, 4, 5, 6 );

			play( idle );
		}

		@Override
		public int blood() {
			return 0xFF00FFFF;
		}

		@Override
		public void die() {
			super.die();
			emitter().start( ShaftParticle.FACTORY, 0.3f, 4 );
			emitter().start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );
		}
	}
}

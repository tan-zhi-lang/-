

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LifeLink;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.DirectableAlly;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Stasis extends ClericSpell {

	public static Stasis INSTANCE = new Stasis();

	@Override
	public int icon() {
		return HeroIcon.STASIS;
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", 30 + 30*Dungeon.hero.天赋点数(Talent.STASIS)) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	@Override
	public boolean canCast(Hero hero) {
		return super.canCast(hero)
				&& (PowerOfMany.getPoweredAlly() != null || hero.buff(StasisBuff.class) != null);
	}

	@Override
	public int chargeUse(Hero hero) {
		if (hero.buff(StasisBuff.class) != null){
			return 0;
		}
		return 2;
	}

	@Override
	public void onCast(神圣法典 tome, Hero hero) {

		onSpellCast(tome, hero);

		if (hero.buff(StasisBuff.class) != null){
			hero.sprite.operate();
			hero.buff(StasisBuff.class).act();
			return;
		}

		Char ally = PowerOfMany.getPoweredAlly();

		hero.sprite.zap(ally.pos);
		MagicMissile.boltFromChar(hero.sprite.parent, MagicMissile.LIGHT_MISSILE, ally.sprite, hero.pos, null);

		LinkedHashSet<Buff> buffs = ally.buffs();
		Actor.remove(ally);
		ally.sprite.killAndErase();
		ally.sprite = null;
		Dungeon.level.mobs.remove(ally);
		for (Buff b : buffs){
			if (b.type == Buff.buffType.POSITIVE || b.revivePersists) {
				ally.add(b);
			}
		}
		ally.clearTime();

		Buff.延长(hero, StasisBuff.class, 30 + 30*hero.天赋点数(Talent.STASIS)).stasisAlly = (Mob)ally;
		Sample.INSTANCE.play(Assets.Sounds.TELEPORT);

		if (hero.buff(LifeLink.class) != null && hero.buff(LifeLink.class).object == ally.id()){
			hero.buff(LifeLink.class).detach();
		}

		hero.spendAndNext(Actor.TICK);
		Dungeon.observe();
		GameScene.updateFog();

	}

	public static Char getStasisAlly(){
		if (Dungeon.hero() && Dungeon.hero.buff(StasisBuff.class) != null){
			return Dungeon.hero.buff(StasisBuff.class).stasisAlly;
		}
		return null;
	}

	public static class StasisBuff extends FlavourBuff {

		{
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.MANY_POWER;
		}

		@Override
		public float iconFadePercent() {
			int duration = 30 + 30*Dungeon.hero.天赋点数(Talent.STASIS);
			return Math.max(0, (duration - visualcooldown()) / duration);
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", Messages.titleCase(stasisAlly.name()), dispTurns());
		}

		@Override
		public boolean act() {
			ArrayList<Integer> spawnPoints = new ArrayList<>();
			for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
				int p = target.pos + PathFinder.NEIGHBOURS8[i];
				if (Actor.findChar(p) == null
						&& (Dungeon.level.passable[p] || (stasisAlly.flying && Dungeon.level.avoid[p])) ){
					spawnPoints.add(p);
				}
			}
			if (spawnPoints.isEmpty()){
				spawnPoints.add(target.pos + PathFinder.NEIGHBOURS8[Random.Int(8)]);
			}
			stasisAlly.pos = Random.element(spawnPoints);
			GameScene.add(stasisAlly);

			if (stasisAlly instanceof DirectableAlly){
				((DirectableAlly) stasisAlly).clearDefensingPos();
			}

			if (stasisAlly.buff(LifeLink.class) != null){
				Buff.延长(Dungeon.hero, LifeLink.class, stasisAlly.buff(LifeLink.class).cooldown()).object = stasisAlly.id();
			}

			ScrollOfTeleportation.appear(stasisAlly, stasisAlly.pos);
			Sample.INSTANCE.play(Assets.Sounds.TELEPORT);

			return super.act();
		}

		Mob stasisAlly;

		private static final String ALLY = "ally";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(ALLY, stasisAlly);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			stasisAlly = (Mob)bundle.get(ALLY);
		}
	}

}

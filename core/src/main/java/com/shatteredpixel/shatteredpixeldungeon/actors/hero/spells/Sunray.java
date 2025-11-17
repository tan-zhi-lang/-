

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Sunray extends TargetedClericSpell {

	public static final Sunray INSTANCE = new Sunray();

	@Override
	public int icon() {
		return HeroIcon.SUNRAY;
	}

	@Override
	public String desc() {
		int min = Dungeon.hero.天赋点数(Talent.日耀射线,4);
		int max = Dungeon.hero.天赋点数(Talent.日耀射线,8);
		int dur = Dungeon.hero.天赋点数(Talent.日耀射线);
		return Messages.get(this, "desc", min, max, dur) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	@Override
	protected void onTargetSelected(神圣法典 tome, Hero hero, Integer target) {
		if (target == null){
			return;
		}

		Ballistica aim = new Ballistica(hero.pos, target,  targetingFlags());

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
		Sample.INSTANCE.play( Assets.Sounds.RAY );
		hero.sprite.zap(target);

		hero.sprite.parent.add(
				new Beam.SunRay(hero.sprite.center(), DungeonTilemap.raisedTileCenterToWorld(aim.collisionPos)));

		Char ch = Actor.findChar( aim.collisionPos );
		if (ch != null) {
			ch.sprite.burst(0xFFFFFF44, 5);

			if (Char.hasProp(ch, Char.Property.UNDEAD) || Char.hasProp(ch, Char.Property.DEMONIC)){
				ch.受伤时(hero.天赋点数(Talent.日耀射线,8),Sunray.this);
			} else {
					ch.受伤时(Random.NormalIntRange(
							hero.天赋点数(Talent.日耀射线,4),
							hero.天赋点数(Talent.日耀射线,8)
							), Sunray.this);
			}

			if (ch.isAlive()) {
				if (ch.buff(Blindness.class) != null && ch.buff(SunRayRecentlyBlindedTracker.class) != null) {
					Buff.延长(ch, Paralysis.class, hero.天赋点数(Talent.日耀射线));
					ch.buff(SunRayRecentlyBlindedTracker.class).detach();
				} else if (ch.buff(SunRayUsedTracker.class) == null) {
					Buff.延长(ch, Blindness.class, hero.天赋点数(Talent.日耀射线));
					Buff.延长(ch, SunRayRecentlyBlindedTracker.class, hero.天赋点数(Talent.日耀射线));
					Buff.施加(ch, SunRayUsedTracker.class);
				}
				if (hero.subClass == HeroSubClass.PRIEST){
					Buff.施加(ch, GuidingLight.Illuminated.class);
				}
			}
		}

		hero.spend( 1f );
		hero.next();

		onSpellCast(tome, hero);

	}

	public static class SunRayUsedTracker extends Buff {}
	public static class SunRayRecentlyBlindedTracker extends FlavourBuff {}

}



package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DwarfKing;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.BArray;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class WandOfLightning extends DamageWand {

	{
		image = 物品表.WAND_LIGHTNING;
	}
	
	private ArrayList<Char> affected = new ArrayList<>();

	private ArrayList<Lightning.Arc> arcs = new ArrayList<>();

	public int min(int lvl){
		return 5+lvl;
	}

	public int max(int lvl){
		return 10+5*lvl;
	}
	
	@Override
	public void onZap(Ballistica bolt) {

		for (Char ch : affected.toArray(new Char[0])){
			if (ch != curUser && ch.alignment == curUser.alignment && ch.pos != bolt.collisionPos){
				affected.remove(ch);
			} else if (ch.buff(LightningCharge.class) != null){
				affected.remove(ch);
			}
		}

		//lightning deals less damage per-target, the more targets that are hit.
		float multiplier = 0.4f + (0.6f/affected.size());
		//if the main target is in water, all affected take full damage
		if (Dungeon.level.water[bolt.collisionPos]) multiplier = 1f;

		for (Char ch : affected){
			if (ch == Dungeon.hero) PixelScene.shake( 2, 0.3f );
			ch.sprite.centerEmitter().burst( SparkParticle.FACTORY, 3 );
			ch.sprite.flash();

			wandProc(ch, chargesPerCast());
			if (ch == curUser && ch.isAlive()) {
				ch.受伤时(Math.round(damageRoll() * multiplier * 0.5f), this);
				if (!curUser.isAlive()) {
					Badges.validateDeathFromFriendlyMagic();
					Dungeon.fail( this );
					GLog.n(Messages.get(this, "ondeath"));
				}
			} else {
				ch.受伤时(Math.round(damageRoll() * multiplier), this);
			}
		}
	}

	@Override
	public void onHit(法师魔杖 staff, Char attacker, Char defender, int damage) {

		// lvl 0 - 25%
		// lvl 1 - 40%
		// lvl 2 - 50%
		float procChance = (强化等级()+1f)/(强化等级()+4f) * procChanceMultiplier(attacker);
		if (Random.Float() < procChance) {

			float powerMulti = Math.min(1f, procChance);

			FlavourBuff.延长(attacker, LightningCharge.class, powerMulti*LightningCharge.DURATION);
			attacker.sprite.centerEmitter().burst( SparkParticle.FACTORY, 10 );
			attacker.sprite.flash();
			Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );

		}
	}

	public static class LightningCharge extends FlavourBuff {

		{
			type = buffType.POSITIVE;
		}

		public static float DURATION = 10f;

		@Override
		public int icon() {
			return BuffIndicator.IMBUE;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(1, 1, 0);
		}
	}

	public static class LightningOnHit extends Shocking {
		@Override
		protected float procChanceMultiplier(Char attacker) {
			return Wand.procChanceMultiplier(attacker);
		}
	}

	private void arc( Char ch ) {

		int dist = Dungeon.level.water[ch.pos] ? 2 : 1;

		if (curUser.buff(LightningCharge.class) != null){
			dist++;
		}

		ArrayList<Char> hitThisArc = new ArrayList<>();
		PathFinder.buildDistanceMap( ch.pos, BArray.not( Dungeon.level.solid, null ), dist );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE){
				Char n = Actor.findChar( i );
				if (n == Dungeon.hero && PathFinder.distance[i] > 1)
					//the hero is only zapped if they are adjacent
					continue;
				else if (n != null && !affected.contains( n )) {
					hitThisArc.add(n);
				}
			}
		}
		
		affected.addAll(hitThisArc);
		for (Char hit : hitThisArc){
			arcs.add(new Lightning.Arc(ch.sprite.center(), hit.sprite.center()));
			arc(hit);
		}
	}
	
	@Override
	public void fx(Ballistica bolt, Callback callback) {

		affected.clear();
		arcs.clear();

		int cell = bolt.collisionPos;

		Char ch = Actor.findChar( cell );
		if (ch != null) {
			if (ch instanceof DwarfKing){
				Statistics.qualifiedForBossChallengeBadge = false;
			}

			affected.add( ch );
			arcs.add( new Lightning.Arc(curUser.sprite.center(), ch.sprite.center()));
			arc(ch);
		} else {
			arcs.add( new Lightning.Arc(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld(bolt.collisionPos)));
			CellEmitter.center( cell ).burst( SparkParticle.FACTORY, 3 );
		}

		//don't want to wait for the effect before processing damage.
		curUser.sprite.parent.addToFront( new Lightning( arcs, null ) );
		Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );
		callback.call();
	}

	@Override
	public void staffFx(法师魔杖.StaffParticle particle) {
		particle.color(0xFFFFFF);
		particle.am = 0.6f;
		particle.setLifespan(0.6f);
		particle.acc.set(0, +10);
		particle.speed.polar(-Random.Float(3.1415926f), 6f);
		particle.setSize(0f, 1.5f);
		particle.sizeJitter = 1f;
		particle.shuffleXY(1f);
		float dst = Random.Float(1f);
		particle.x -= dst;
		particle.y += dst;
	}
	
}



package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BlastParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blazing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 冰海法杖 extends DamageWand {

	{
		image = 物品表.冰海法杖;

		//only used for targeting, actual projectile logic is Ballistica.STOP_SOLID | Ballistica.IGNORE_SOFT_SOLID
		collisionProperties = Ballistica.WONT_STOP;
	}

	//1/2/3 base damage with 1/2/3 scaling based on charges used
	public int min(int lvl){
		return (1+lvl) * chargesPerCast()*3;
	}

	//2/8/18 base damage with 2/4/6 scaling based on charges used
	public int max(int lvl){
		switch (chargesPerCast()){
			case 1: default:
				return (2 + 2*lvl)*3;
			case 2:
				return 3*2*(4 + 2*lvl);
			case 3:
				return 3*3*(6+2*lvl);
		}
	}

	ConeAOE cone;

	@Override
	public void onZap(Ballistica bolt) {

		ArrayList<Char> affectedChars = new ArrayList<>();
		ArrayList<Integer> adjacentCells = new ArrayList<>();
		for( int cell : cone.cells ){

			//ignore caster cell
			if (cell == bolt.sourcePos){
				continue;
			}

			//knock doors open
			if (Dungeon.level.map[cell] == Terrain.DOOR){
				Level.set(cell, Terrain.OPEN_DOOR);
				GameScene.updateMap(cell);
			}

			//only ignite cells directly near caster if they are flammable or solid
			if (Dungeon.level.adjacent(bolt.sourcePos, cell)
					&& !(Dungeon.level.flamable[cell] || Dungeon.level.solid[cell])){
				adjacentCells.add(cell);
				//do burn any heaps located here though
				if (Dungeon.level.heaps.get(cell) != null){
					Dungeon.level.heaps.get(cell).burn();
				}
			} else {
				GameScene.add( Blob.seed( cell, 1+chargesPerCast(), Freezing.class));
			}

			Char ch = Actor.findChar( cell );
			if (ch != null) {
				affectedChars.add(ch);
			}
		}

		//if wand was shot right at a wall
		if (cone.cells.isEmpty()){
			adjacentCells.add(bolt.sourcePos);
		}

		//ignite cells that share a side with an adjacent cell, are flammable, and are closer to the collision pos
		//This prevents short-range casts not igniting barricades or bookshelves
		for (int cell : adjacentCells){
			for (int i : PathFinder.NEIGHBOURS8){
				if (Dungeon.level.trueDistance(cell+i, bolt.collisionPos) < Dungeon.level.trueDistance(cell, bolt.collisionPos)
						&& Dungeon.level.flamable[cell+i]
						&& Freezing.volumeAt(cell+i, Freezing.class) == 0){
					GameScene.add( Blob.seed( cell+i, 1+chargesPerCast(), Freezing.class ) );
				}
			}
		}

		for ( Char ch : affectedChars ){
			wandProc(ch, chargesPerCast());
			ch.受伤时(damageRoll(), this);
			if (ch.isAlive()) {
				Buff.施加(ch,Chill.class,Chill.DURATION);
				switch (chargesPerCast()) {
					case 1:
						break; //no effects
					case 2:
						Buff.施加(ch,Cripple.class,5);
						break;
					case 3:
						Buff.施加(ch, Frost.class, Frost.DURATION);
						break;
				}
			}
		}
	}

	@Override
	public void onHit(法师魔杖 staff, Char attacker, Char defender, float damage) {

		//proc chance is initially 0..
		float procChance = 0;
		for (int i : PathFinder.NEIGHBOURS9) {

			//+25% proc chance per burning char within 3x3 of target
			// this includes the attacker and defender
			if (Actor.findChar(defender.pos + i) != null
					&& Actor.findChar(defender.pos + i).buff(Chill.class) != null){
				procChance+=
						0.25f;
			}else if (Actor.findChar(defender.pos + i) != null
					&& Actor.findChar(defender.pos + i).buff(Frost.class) != null) {
				procChance += 0.5f;

			//otherwise +5% proc chance per burning tile within 3x3 of target
			} else if (Freezing.volumeAt(defender.pos+i, Freezing.class) > 0){
				procChance += 0.05f;
			}

		}

		procChance = Math.min(1f, procChance);
		procChance *= Wand.procChanceMultiplier(attacker);

		if (Random.Float() < procChance){

			float powerMulti = Math.max(1f, procChance);

			Blob free = Dungeon.level.blobs.get(Freezing.class);

			//explode, dealing damage to enemies in 3x3, and clearing all free
			CellEmitter.center(defender.pos).burst(BlastParticle.FACTORY, 30);
			if (free != null) {
				for (int i : PathFinder.NEIGHBOURS9) {
					CellEmitter.get(defender.pos + i).burst(SmokeParticle.FACTORY, 4);
					if (Freezing.volumeAt(defender.pos+i, Freezing.class) > 0){
						Dungeon.level.destroy(defender.pos + i);
						GameScene.updateMap(defender.pos + i);
						free.clear(defender.pos + i);
					}

					Char ch = Actor.findChar(defender.pos + i);
					if (ch != null) {
						if (ch.buff(Chill.class) != null) {
							ch.buff(Chill.class).detach();
						}
						if (ch.alignment == Char.Alignment.ENEMY) {
							//damage of a 2-charge zap
							ch.受伤时(powerMulti*Random.NormalIntRange(2 + 2*强化等级(), 8 + 4* 强化等级()), this);
						}
					}
				}
			}

			Sample.INSTANCE.play( Assets.Sounds.BLAST );

		}
	}

	public static class FreeBlastOnHit extends Blazing {
		@Override
		protected float procChanceMultiplier(Char attacker) {
			return Wand.procChanceMultiplier(attacker);
		}
	}

	@Override
	public void fx(Ballistica bolt, Callback callback) {
		//need to perform flame spread logic here so we can determine what cells to put flames in.

		// 5/7/9 distance
		int maxDist = 3 + 2*chargesPerCast();

		cone = new ConeAOE( bolt,
				maxDist,
				30 + 20*chargesPerCast(),
				Ballistica.STOP_TARGET | Ballistica.STOP_SOLID | Ballistica.IGNORE_SOFT_SOLID);

		//cast to cells at the tip, rather than all cells, better performance.
		Ballistica longestRay = null;
		for (Ballistica ray : cone.outerRays){
			if (longestRay == null || ray.dist > longestRay.dist){
				longestRay = ray;
			}
			((MagicMissile)curUser.sprite.parent.recycle( MagicMissile.class )).reset(
					MagicMissile.FROST_CONE,
					curUser.sprite,
					ray.path.get(ray.dist),
					null
			);
		}

		//final zap at half distance of the longest ray, for timing of the actual wand effect
		MagicMissile.boltFromChar( curUser.sprite.parent,
				MagicMissile.FROST_CONE,
				curUser.sprite,
				longestRay.path.get(longestRay.dist/2),
				callback );
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
		Sample.INSTANCE.play( Assets.Sounds.BURNING );
	}

	@Override
	protected int chargesPerCast() {
		if (cursed ||
				(charger != null && charger.target != null && charger.target.buff(WildMagic.WildMagicTracker.class) != null)){
			return 1;
		}
		//consumes 30% of current charges, rounded up, with a min of 1 and a max of 3.
		return (int) GameMath.gate(1, (int)Math.ceil(curCharges*0.3f), 3);
	}

	@Override
	public String statsDesc() {
		if (levelKnown)
			return Messages.get(this, "stats_desc", chargesPerCast(), min(), max());
		else
			return Messages.get(this, "stats_desc", chargesPerCast(), min(0), max(0));
	}

	@Override
	public String upgradeStat1(int level) {
		return (1+level) + "-" + (2+2*level);
	}

	@Override
	public String upgradeStat2(int level) {
		return (2+2*level) + "-" + 2*(4+2*level);
	}

	@Override
	public String upgradeStat3(int level) {
		return (3+3*level) + "-" + 3*(6+2*level);
	}

	@Override
	public void staffFx(法师魔杖.StaffParticle particle) {
		particle.color(0x88CCFF);
		particle.am = 0.6f;
		particle.setLifespan(0.6f);
		particle.acc.set(0, -40);
		particle.setSize( 0f, 3f);
		particle.shuffleXY( 1.5f );
	}

}

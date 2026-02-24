

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Web;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.PurpleParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.utils.Callback;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 影织法杖 extends DamageWand {

	{
		image = 物品表.影织法杖;

		collisionProperties = Ballistica.WONT_STOP;
	}

	//1/2/3 base damage with 1/2/3 scaling based on charges used
	public float min(int lvl){
		return 魔力(0.3f,1) * chargesPerCast();
	}

	//2/8/18 base damage with 2/4/6 scaling based on charges used
	public float max(int lvl){
		switch (chargesPerCast()){
			case 1: default:
				return 魔力(0.4f,1);
			case 2:
			return 魔力(2f,0.5f);
			case 3:
			return 魔力(2f,0.3f);
		}
	}

	ConeAOE cone;
	@Override
	public int targetingPos(Hero user,int dst) {
		if (!cursed || !cursedKnown) {
			return dst;
		} else {
			return super.targetingPos(user, dst);
		}
	}
	@Override
	public void onZap(Ballistica bolt) {

		ArrayList<Char> affectedChars = new ArrayList<>();
		ArrayList<Integer> adjacentCells = new ArrayList<>();
		Blob
				web = Dungeon.level.blobs.get(Web.class);
		boolean terrainAffected = false;
		int terrainPassed = 2, terrainBonus = 0;
		for( int cell : cone.cells ){

			//ignore caster cell
			if (cell == bolt.sourcePos){
				continue;
			}
			if (Dungeon.level.flamable[cell]) {
				Dungeon.level.destroy( cell );
				GameScene.updateMap( cell );
				terrainAffected = true;

			}

			Char ch = Actor.findChar( cell );
			if (ch != null) {
				terrainBonus += terrainPassed/3;
				terrainPassed = terrainPassed%3;

				if (ch instanceof Mob&&((Mob) ch).state==((Mob) ch).PASSIVE
					&&!(Dungeon.level.mapped[cell] || Dungeon.level.visited[cell])){
					//avoid harming undiscovered passive chars
				} else {
					affectedChars.add(ch);
				}
			}
			if (Dungeon.level.solid[cell]) {
				terrainPassed++;
			}
			if (Dungeon.level.flamable[cell]) {
				Dungeon.level.destroy( cell );
				GameScene.updateMap( cell );
				terrainAffected = true;

			}
			CellEmitter.center(cell).burst(PurpleParticle.BURST,Random.IntRange(1,2));
		}

		//if wand was shot right at a wall
		if (cone.cells.isEmpty()){
			adjacentCells.add(bolt.sourcePos);
		}
		if (terrainAffected) {
			Dungeon.observe();
		}

		int level = 强化等级();
		int lvl = level + (affectedChars.size()-1) + terrainBonus;
		for (Char ch : affectedChars) {
			wandProc(ch, chargesPerCast());
			ch.受伤时( damageRoll(lvl), this );
			if (ch.isAlive()) {
				switch (chargesPerCast()) {
					case 1:
						Buff.施加(ch,Cripple.class,Cripple.DURATION/4f);
						break; //no effects
					case 2:
						Buff.施加(ch,Cripple.class,Cripple.DURATION/2f);
						break;
					case 3:
						Buff.施加(ch,Cripple.class,Cripple.DURATION);
						break;
				}
			}
			ch.sprite.centerEmitter().burst( PurpleParticle.BURST );
			ch.sprite.flash();
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
			int cell = ray.path.get(ray.dist);
			curUser.sprite.parent.add(new Beam.DeathRay(curUser.sprite.center(),DungeonTilemap.raisedTileCenterToWorld(cell)));

		}
		callback.call();
	}

	@Override
	protected int chargesPerCast() {
		if (cursed ||
				(charger != null && charger.target != null && charger.target.buff(WildMagic.WildMagicTracker.class) != null)){
			return 1;
		}
		//consumes 30% of current charges, rounded up, with a min of 1 and a max of 3.
		return (int) GameMath.之内(1,(int)Math.ceil(curCharges*0.3f),3);
	}

	@Override
	public String statsDesc() {
		if (levelKnown)
			return Messages.get(this, "stats_desc", chargesPerCast(), min(), max());
		else
			return Messages.get(this, "stats_desc", chargesPerCast(), min(0), max(0));
	}

	@Override
	public void staffFx(法师魔杖.StaffParticle particle) {
		particle.color(0x220022);
		particle.am = 0.6f;
		particle.setLifespan(0.6f);
		particle.acc.set(0, -40);
		particle.setSize( 0f, 3f);
		particle.shuffleXY( 1.5f );
	}

}

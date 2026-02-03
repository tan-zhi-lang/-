

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StenchGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.RatSkull;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.FetidRatSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class FetidRat extends Rat {

	{
		spriteClass = FetidRatSprite.class;

		生命 = 最大生命 = 20;
		defenseSkill = 5;
		普通=false;

		经验 = 4;
		loot =new RatSkull();
		WANDERING = new Wandering();
		state = WANDERING;

		properties.add(Property.MINIBOSS);
		properties.add(Property.DEMONIC);
	}

	@Override
	public int 最大命中(Char target ) {
		return 12;
	}

	@Override
	public float 最大防御() {
		return super.最大防御()+2;
	}

	@Override
	public float 攻击时(final Char enemy, float damage ) {
		damage = super.攻击时( enemy, damage );
		if (Random.Int(3) == 0) {
			Buff.施加(enemy, Ooze.class).set( Ooze.DURATION );
			//score loss is on-hit instead of on-attack because it's tied to ooze
			if (enemy == Dungeon.hero && !Dungeon.level.water[enemy.pos]){
				Statistics.questScores[0] -= 50;
			}
		}

		return damage;
	}

	@Override
	public float 防御时(Char enemy, float damage ) {

		GameScene.add(Blob.seed(pos, 20, StenchGas.class));

		return super.防御时(enemy, damage);
	}

	@Override
	public void 死亡时(Object cause ) {
		super.死亡时( cause );

		Ghost.Quest.process();
	}

	protected class Wandering extends Mob.Wandering{
		@Override
		protected int randomDestination() {
			//of two potential wander positions, picks the one closest to the hero
			int pos1 = super.randomDestination();
			int pos2 = super.randomDestination();
			PathFinder.buildDistanceMap(Dungeon.hero.pos, Dungeon.level.passable);
			if (PathFinder.distance[pos2] < PathFinder.distance[pos1]){
				return pos2;
			} else {
				return pos1;
			}
		}
	}
	
	{
		immunities.add( StenchGas.class );
	}
}
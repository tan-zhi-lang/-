

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;
import com.shatteredpixel.shatteredpixeldungeon.plants.Rotberry;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RotHeartSprite;
import com.watabou.utils.PathFinder;

public class RotHeart extends Mob {

	{
		spriteClass = RotHeartSprite.class;

		生命 = 最大生命 = 80;
		defenseSkill = 0;

		经验 = 4;

		state = PASSIVE;

		properties.add(Property.IMMOVABLE);
		properties.add(Property.MINIBOSS);
		properties.add(Property.STATIC);
	}

	@Override
	protected boolean act() {
		alerted = false;
		return super.act();
	}

	@Override
	public void 受伤时(int dmg, Object src) {
		//TODO: when effect properties are done, change this to FIRE
		if (src instanceof 燃烧) {
			destroy();
			sprite.die();
		} else {
			super.受伤时(dmg, src);
		}
	}

	@Override
	public int 防御时(Char enemy, int damage) {
		//rot heart spreads less gas in enclosed spaces
		int openNearby = 0;
		for (int i : PathFinder.NEIGHBOURS8){
			if (!Dungeon.level.solid[pos+i]){
				openNearby++;
			}
		}

		GameScene.add(Blob.seed(pos, 5 + 3*openNearby, ToxicGas.class));

		return super.防御时(enemy, damage);
	}

	@Override
	public void beckon(int cell) {
		//do nothing
	}

	@Override
	protected boolean getCloser(int target) {
		return false;
	}

	@Override
	public void destroy() {
		super.destroy();
		Bestiary.skipCountingEncounters = true;
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[Dungeon.level.mobs.size()])){
			if (mob instanceof RotLasher){
				mob.死亡时(null);
			}
		}
		Bestiary.skipCountingEncounters = false;
	}

	@Override
	public void 死亡时(Object cause) {
		super.死亡时(cause);
		Dungeon.level.drop( new Rotberry.Seed(), pos ).sprite.drop();
		//assign score here as player may choose to keep the rotberry seed
		Statistics.questScores[1] += 2000;
	}

	@Override
	public boolean reset() {
		return true;
	}

	@Override
	public int 最小攻击() {
		return 0;
	}
	@Override
	public int 最大攻击() {
		return 0;
	}

	@Override
	public int 最大命中(Char target ) {
		return 0;
	}

	@Override
	public int 最大防御() {
		return super.最大防御()+5;
	}
	
	{
		immunities.add( ToxicGas.class );
	}

}



package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SwarmSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Swarm extends Mob {

	{
		spriteClass = SwarmSprite.class;
		
		生命 = 最大生命 = 50;
		defenseSkill = 5;

		经验 = 3;
		最大等级 = 9;
		
		flying = true;

		loot = PotionOfHealing.class;
		lootChance = 0.1667f; //by default, see lootChance()
	}
	
	private static final float SPLIT_DELAY	= 1f;
	
	int generation	= 0;
	
	private static final String GENERATION	= "generation";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( GENERATION, generation );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		generation = bundle.getInt( GENERATION );
		if (generation > 0) 经验 = 0;
	}

	@Override
	public void 死亡时(Object cause) {
		flying = false;
		super.死亡时(cause);
	}
	
	@Override
	public int 攻击() {
		return Random.NormalIntRange( 1, 4 );
	}
	
	@Override
	public int 防御时(Char enemy, int damage ) {

		if (生命 >= damage + 2) {
			ArrayList<Integer> candidates = new ArrayList<>();
			
			int[] neighbours = {pos + 1, pos - 1, pos + Dungeon.level.width(), pos - Dungeon.level.width()};
			for (int n : neighbours) {
				if (!Dungeon.level.solid[n]
						&& Actor.findChar( n ) == null
						&& (Dungeon.level.passable[n] || Dungeon.level.avoid[n])
						&& (!properties().contains(Property.LARGE) || Dungeon.level.openSpace[n])) {
					candidates.add( n );
				}
			}
	
			if (candidates.size() > 0) {
				
				Swarm clone = split();
				clone.pos = Random.element( candidates );
				clone.state = clone.HUNTING;
				GameScene.add( clone, SPLIT_DELAY ); //we add before assigning HP due to ascension

				clone.生命 = (生命 - damage) / 2;
				Actor.add( new Pushing( clone, pos, clone.pos ) );

				Dungeon.level.occupyCell(clone);
				
				生命 -= clone.生命;
			}
		}
		
		return super.防御时(enemy, damage);
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 10;
	}
	
	private Swarm split() {
		Swarm clone = new Swarm();
		clone.generation = generation + 1;
		clone.经验 = 0;
		if (buff( Burning.class ) != null) {
			Buff.施加( clone, Burning.class ).reignite( clone );
		}
		if (buff( Poison.class ) != null) {
			Buff.施加( clone, Poison.class ).set(2);
		}
		for (Buff b : buffs()){
			if (b.revivePersists) {
				Buff.施加(clone, b.getClass());
			}
		}
		return clone;
	}

	@Override
	public float lootChance() {
		lootChance = 1f/(6 * (generation+1) );
		return super.lootChance() * (5f - Dungeon.LimitedDrops.SWARM_HP.count) / 5f;
	}
	
	@Override
	public Item createLoot(){
		Dungeon.LimitedDrops.SWARM_HP.count++;
		return super.createLoot();
	}
}
